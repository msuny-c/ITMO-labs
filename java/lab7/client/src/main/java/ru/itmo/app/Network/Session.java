package ru.itmo.app.Network;

public class Session {
    public static User user = new User(null, null);
    public static void setUser(User user) {
        Session.user = user;
    }
}
