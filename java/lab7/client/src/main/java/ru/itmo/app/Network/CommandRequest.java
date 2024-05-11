package ru.itmo.app.Network;
import ru.itmo.app.Models.HumanBeing;

import java.io.Serial;
import java.io.Serializable;

public record CommandRequest(Action action, String command, HumanBeing object, String[] args, String login, String pass) implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098265757690L;
}
