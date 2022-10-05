package lab.utility;

import lab.commands.Command;
import lab.data.SpaceMarine;
import lab.exeptions.EmptyElement;
import lab.exeptions.IncorrectData;

import java.io.Serializable;

public class Message implements Serializable {
    private Command command;
    private Object data;
    private SpaceMarine spaceMarine;

    public void loadPreMessage(Command command, String[] argument) throws EmptyElement, IncorrectData {
        this.command = command;
        data = argument;
        if (command.getName().equals("add") || command.getName().equals("add_if_max") || command.getName().equals("update")) {
            spaceMarine = ((SpaceMarineArgumentLoader) data).loadSpaceMarin();
        }

    }

    public Command getCommand() {
        return command;
    }

    public Object getData() {
        return data;
    }

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }
}
