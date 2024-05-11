package ru.itmo.app.Managers;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.RandomStringUtils;

public class HashManager {
    public String getHash(String pass) {
        return Hashing.md5().hashString(pass, StandardCharsets.UTF_8).toString();
    }
    public String generateSalt() {
        return RandomStringUtils.randomAscii(128);
    }
}
