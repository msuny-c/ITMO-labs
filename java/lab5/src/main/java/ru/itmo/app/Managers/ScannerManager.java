package ru.itmo.app.Managers;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Class to store scanner.
 */
public class ScannerManager {
    /**
     * Checks if current stream is System.in
     */
    public boolean isSystemIn;
    public ScannerManager(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        isSystemIn = true;
    }
    public ScannerManager(InputStreamReader inputStreamReader) {
        scanner = new Scanner(inputStreamReader);
    }
    public final Scanner scanner;
}
