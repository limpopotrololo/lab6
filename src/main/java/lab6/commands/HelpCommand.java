package lab6.commands;

import lab6.exeptions.*;
import lab6.utility.ArgumentLoader;
import lab6.utility.CommandPool;
import lab6.utility.IOManager;
import java.util.HashMap;
import java.util.Map;

/**
 * "help" command, print list of available command and description
 */
public class HelpCommand extends Command {
    private CommandPool commands;
    private final IOManager ioManager;

    public HelpCommand(CommandPool commandPool, IOManager ioManager) {
        commands = commandPool;
        this.ioManager = ioManager;
    }

    /**
     * execute command
     * @param arguments
     * @throws EmptyElement
     * @throws IncorrectData
     */
    @Override
    public void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData {
        try {
            arguments.validateCount(0);
            HashMap<String, Command> pool = commands.getCommands();
            for (Map.Entry<String, Command> entry : pool.entrySet()) {

                ioManager.println(entry.getValue().getName() + "----" + entry.getValue().getDescription() + "\n");

            }
        } catch (IllegalArgumentException e) {
            ioManager.printerr("Неверное количество аргументов");
        }


    }

    public String getDescription() {
        return "Выводит список и функционал всех команд";
    }

    public String getName() {
        return "help";
    }
}
