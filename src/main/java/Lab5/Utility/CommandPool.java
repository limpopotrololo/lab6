package Lab5.Utility;

import Lab5.Commands.Command;

import java.util.HashMap;

public class CommandPool {
    private final HashMap<String, Command> commands = new HashMap<>();

    public Command get(String key) {
        if (!commands.containsKey(key))
            throw new IllegalArgumentException("Incorrect command");

        return commands.get(key);
    }

    public void upload(Command command) {
        commands.put(command.getName(), command);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}
