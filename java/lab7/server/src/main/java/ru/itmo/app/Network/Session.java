package ru.itmo.app.Network;

public class Session {
    private static final ThreadLocal<String> currentUser = new ThreadLocal<>();
    public static String getCurrentUser() {
        return currentUser.get();
    }
    public static void setCurrentUser(String user) {
        currentUser.set(user);
    }
    public static void removeCurrentUser() {
        currentUser.remove();
    }
}
