package ru.itmo.app.Database;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
import ru.itmo.app.Interfaces.IHashManager;

import java.nio.charset.StandardCharsets;

public class HashManager implements IHashManager {
    @Override
    public String getHash(String string) {
        return Hashing.md5().hashString(string, StandardCharsets.UTF_8).toString();
    }

    @Override
    public String generateString(int length) {
        return RandomStringUtils.randomAscii(length);
    }
}
