package lab6.utility;

import lab6.commands.Command;
import java.util.HashMap;

/**
 * Pool of commands
 */
public class CommandPool {
    private final HashMap<String, Command> commands = new HashMap<>();

    public Command get(String key) {
        if (!commands.containsKey(key))
            throw new IllegalArgumentException();

        return commands.get(key);
    }

    public void upload(Command command) {
        commands.put(command.getName(), command);
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

}
