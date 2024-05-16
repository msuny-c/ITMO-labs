package ru.itmo.app.Utilities;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;

public class HashUtils {
    public static String hash(String string) {
        return Hashing.md5().hashString(string, StandardCharsets.UTF_8).toString();
    }
    public static String generateString(int length) {
        return RandomStringUtils.randomAscii(length);
    }
}
