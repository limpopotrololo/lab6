package Lab5.Commands;

import Lab5.Exeptions.*;
import Lab5.Utility.ArgumentLoader;
import Lab5.Utility.CommandPool;
import Lab5.Utility.IOManager;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends Command {
    private CommandPool commands;
    private final IOManager ioManager;

    public HelpCommand(CommandPool commandPool, IOManager ioManager) {
        commands = commandPool;
        this.ioManager = ioManager;
    }


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
