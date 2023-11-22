import re
import time

input_file = open('schedule.yaml', 'r', encoding='utf-8')
output_file = open('schedule.xml', 'w', encoding='utf-8')
lines = input_file.readlines()


def has_key(s):
    return bool(s.count(':')) and s.strip()[0] != '-'


def is_string(s):
    return isinstance(s, str) and s.split() in [['>'], ['|']]


def parse_key(s):
    if has_key(s):
        key_end_index = s.lstrip().find(':')
        return s.lstrip()[:key_end_index].strip('-').strip()


def handle_comment(string):
    balance = [0] * 2
    for i in range(len(string)):
        if string[i] == '"':
            balance[0] += 1 * (balance[1] % 2 != 1)
        elif string[i] == "'":
            balance[1] += 1 * (balance[0] % 2 != 1)
        if balance[0] % 2 == 0 and balance[1] % 2 == 0 and string[i] == '#':
            return string[:i].strip()
    return string


def is_number(string):
    return re.fullmatch(r'^\s*((0x|0b|0)[0-9A-F]*|[0-9]+\.?[0-9]*|yes|Yes|YES|no|No|NO)\s*$', string)


def parse_number(string):
    if is_number(string):
        if string[:2] == '0x':
            return str(int(string[2:], 16))
        elif string[:2] == '0b':
            return str(int(string[2:], 2))
        elif string[:1] == '0':
            if string == '0': return '0'
            return str(int(string[1:], 8))
        elif string in ('yes', 'Yes', 'YES'):
            return 'true'
        elif string in ('no', 'No', 'NO'):
            return 'false'
        elif '.' in string:
            return str(float(string))
        return str(int(string))


def convert_type(string):
    cv_type = re.findall(r'!!(bool|int|float|str)', string)[0]
    cv_value = re.findall(rf'!!{cv_type}\s*(.*)', string)[0]
    if cv_type == 'int':
        return str(int(cv_value))
    if cv_type == 'bool':
        if cv_value in ('yes', 'YES', 'Yes'):
            return 'true'
        elif cv_value in ('no', 'No', 'NO'):
            return 'false'
    elif cv_type == 'float':
        return str(float(cv_value))
    return cv_value


def is_array(string):
    return string.startswith('[') and string.endswith(']')


def is_dict(string):
    return string.startswith('{') and string.endswith('}')


def parse_value(line):
    if has_key(line) and not is_array(line):
        key_end_index = line.find(':')
        value = line[key_end_index + 2:].strip('\n').strip()
    else:
        value = line.strip()
    if ' #' in value or '"#' in value or "'#" in value:
        value = handle_comment(value)
    if is_number(value):
        value = parse_number(value)
    if is_array(value):
        array = value[1:-1].split(',')
        return [parse_value(item) for item in array]
    if is_dict(value):
        dicte = {m.split(':')[0]: parse_value(m.split(':')[1]) for m in value[1:-1].split(',')}
        return dicte
    if re.match(r'!!(bool|int|float|str)', value):
        value = convert_type(value)
    if value.startswith('"'):
        value = value.strip('"')
    elif value.startswith("'"):
        value = value.strip("'")
    return value.strip()

def spaces(s):
    if s.lstrip().lstrip('-').lstrip() != '':
        return s[:s.find(s.lstrip().lstrip('-').lstrip()[0])].count(' ')
    else:
        return 0


def parse_blocks(block, c_i, lines, flags):
    if c_i >= len(lines) or (c_i > 0 and has_key(lines[c_i]) and not has_key(lines[c_i-1]) and not flags) or (c_i > 0 and has_key(lines[c_i]) and spaces(lines[c_i-1]) > spaces(lines[c_i]) and not flags):
        return [block, c_i]
    if has_key(lines[c_i]):
        key = parse_key(lines[c_i])
        value = parse_value(lines[c_i])
        if is_string(value):
            string_type = value.split()[0]
            result = parse_blocks({}, c_i + 1, lines, string_type)
            block.update({key: parse_blocks({}, c_i + 1, lines, string_type)})
            return block
        elif c_i + 1 < len(lines) and not (isinstance(value, str) and value.split()) and spaces(
                lines[c_i + 1]) > spaces(lines[c_i]):
            result = parse_blocks({}, c_i + 1, lines, None)
            block.update({key: result[0]})
            if result[1] < len(lines) and spaces(lines[c_i]) == spaces(lines[result[1]]):
                new_result = parse_blocks({}, result[1], lines, True)
                block.update(new_result[0])
                return [block, new_result[1]]
            return [block, result[1]]
        else:
            block.update({key: value})
            pre_value = lines[c_i][lines[c_i].find(':') + 1:].strip()
            if c_i + 1 < len(lines):
                result = parse_blocks({}, c_i + 1, lines, False)
                if isinstance(value, str) and any(pre_value.startswith(i) for i in ['"', "'"]) and not (len(pre_value) > 1 and pre_value.endswith(pre_value[0])):
                    block[key] += result[0]
                    print(block)
                else:
                    block.update(result[0])
                if result[1] < len(lines) and spaces(lines[result[1]]) == spaces(lines[c_i]):
                    new_result = parse_blocks({}, result[1], lines, True)
                    block.update(new_result[0])
                    return [block, new_result[1]]
                return [block, result[1]]
            else:
                return [block, len(lines)]
    else:
        value = lines[c_i].strip()
        if lines[c_i].strip()[0] == '-':
            value = parse_value(lines[c_i].strip()[1:])
            result = parse_blocks({}, c_i + 1, lines, None)
            block = [value] + list(result[0])
            return [block, result[1]]
        else:
            result = parse_blocks({}, c_i + 1, lines, None)
            value += (result[0] if isinstance(result[0], str) else '')
            return [' ' + parse_value(value) if parse_value(value) else '\n', result[1]]


def parse(blocks=None, tabs=0):
    if blocks is None:
        blocks = parse_blocks({}, 0, lines, None)[0]
    for block in blocks:
        tag = block
        if isinstance(blocks[block], dict):
            output_file.write('\t' * tabs + f'<{tag}>' + '\n')
            parse(blocks[block], tabs + 1)
            output_file.write('\t' * tabs + f'</{tag}>' + '\n')
        else:
            value = blocks[block]
            if isinstance(value, list):
                for v in value:
                    output_file.write('\t' * tabs + f'<{tag}>{v}</{tag}>' + '\n')
            else:
                output_file.write('\t' * tabs + f'<{tag}>{value}</{tag}>' + '\n')


lines = [line for line in lines if handle_comment(line.strip()).strip() != '']
parse()
