package ru.itmo.app.Network;
import ru.itmo.app.Models.HumanBeing;

import java.io.Serial;
import java.io.Serializable;

public record CommandRequest(String command, HumanBeing object, String[] args) implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098265757690L;
}
