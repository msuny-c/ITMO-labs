package ru.itmo.app.Managers;
import ru.itmo.app.Exceptions.EOFException;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class to store scanner.
 */
public class ScannerManager {
    /**
     * Flag if current stream is System.in
     */
    public final boolean isSystemStream;
    private final Scanner scanner;

    public ScannerManager(InputStream inputStream) {
        scanner = new Scanner(inputStream);
        isSystemStream = inputStream.equals(System.in);
    }
    public String nextLine() throws EOFException {
        try {
            return scanner.nextLine();
        } catch (NoSuchElementException exception) {
            throw new EOFException();
        }
    }
    public boolean hasNext() {
        return isSystemStream || scanner.hasNext();
    }
    public void close() {
        scanner.close();
    }
}
