package ru.itmo.app.Utilities;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.RandomStringUtils;
// сделать статиком
public class HashManager {
    public String getHash(String password) {
        return Hashing.md5().hashString(password, StandardCharsets.UTF_8).toString();
    }
    public String generateSalt() {
        return RandomStringUtils.randomAscii(128);
    }
}
