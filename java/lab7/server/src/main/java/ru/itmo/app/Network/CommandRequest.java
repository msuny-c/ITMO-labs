package ru.itmo.app.Network;
import ru.itmo.app.Models.HumanBeing;
import ru.itmo.app.Network.Action;

import java.io.Serial;
import java.io.Serializable;

public record CommandRequest(Action action, String command, HumanBeing object, String[] args, String user, String password) implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098265757690L;
}
