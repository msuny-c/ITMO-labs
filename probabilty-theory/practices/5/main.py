import matplotlib.pyplot as plt
import math

class ProbabilitySolver:
    def __init__(self, numbers):
        self.numbers = numbers
        self.size = len(numbers)
        self.numbers.sort()
    def print_var_series(self):
        print("Вариационный ряд:", self.numbers)
    def print_extreme_numbers(self):
        print(self.numbers[-1], self.numbers[0])
    def print_selection_size(self):
        print(self.numbers[-1] - self.numbers[0])
    def print_disperancy(self):
        mean = sum(self.numbers) / self.size
        sum_squared_dev = sum((x - mean) ** 2 for x in self.numbers)
        std_dev = (sum_squared_dev / (self.size - 1)) ** 0.5
        print('Оценка математического ожидания:', mean)
        print('Оценка среднеквадратического отклонение:', std_dev)
    def show_empiric_function(self, save):
        x_numbers = self.numbers[:]
        y_numbers = [(i + 1) / self.size for i in range(self.size)]
        x_plot = [self.numbers[0] - 1] + x_numbers + [self.numbers[-1] + 1]
        y_plot = [0] + y_numbers + [1]
        plt.figure(figsize=(8, 5))
        plt.step(x_plot, y_plot, where='post', label='Эмпирическая функция распределения')
        plt.xlabel('x')
        plt.ylabel('F*(x)')
        plt.title('График эмпирической функции распределения')
        plt.grid(True)
        plt.legend()
        if save is not None:
            plt.savefig(save)
        plt.show()
    def calc_h(self):
        h = (self.numbers[-1] - self.numbers[0]) / (1 + (math.log(len(self.numbers), 2)))
        return h
    def calc_m(self):
        return int(math.ceil(1 + math.log(len(self.numbers), 2)))
    def show_frequency_polygon(self, save):
        h = self.calc_h()
        M = self.calc_m()
        xStart = self.numbers[0] - h / 2
        class_midpoints = []
        class_frequencies = []
        size = len(self.numbers)
        for i in range(M):
            lower_bound = xStart
            upper_bound = xStart + h
            count = sum(lower_bound <= v < upper_bound for v in self.numbers)
            midpoint = lower_bound + h / 2
            class_midpoints.append(midpoint)
            class_frequencies.append(count / size)
            print("[ {:.2f} : {:.2f} ) -> {:.2f}".format(lower_bound, upper_bound, count / size))
            xStart += h
        plt.plot(class_midpoints, class_frequencies, marker='o')
        plt.xlabel('x')
        plt.ylabel('p_i')
        plt.title('Полигон частот')
        plt.grid(True)
        if save is not None:
            plt.savefig(save)
        plt.show()
    def show_histogram(self, save):
        h = self.calc_h()
        M = self.calc_m()
        xStart = self.numbers[0] - h / 2
        bin_edges = []
        class_densities = []
        size = len(self.numbers)
        for i in range(M):
            lower_bound = xStart
            upper_bound = xStart + h
            count = sum(lower_bound <= v < upper_bound for v in self.numbers)
            density = (count / size) / h
            bin_edges.append(lower_bound)
            class_densities.append(density)
            xStart += h
        bin_edges.append(xStart)
        plt.bar(bin_edges[:-1], class_densities, width=h, align='edge', edgecolor='black', alpha=0.7)
        plt.xlabel('x')
        plt.ylabel('Density')
        plt.title('Гистограмма')
        plt.grid(True)
        if save is not None:
            plt.savefig(save)
        plt.show()

if __name__ == '__main__':
    f = open('input.txt')
    numbers = [float(x) for _ in range(2) for x in f.readline().split()]
    solver = ProbabilitySolver(numbers=numbers)
    solver.print_var_series()
    solver.print_extreme_numbers()
    solver.print_disperancy()
    solver.show_empiric_function(save='empiric-function.png')
    solver.show_frequency_polygon(save='polygon.png')
    solver.show_histogram(save='histogram.png')
    