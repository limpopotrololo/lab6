package lab5.utility;

import lab5.commands.Command;
import lab5.exeptions.*;

/**
 * Class for separating input and pick approach command
 */

public class ConsoleManager {
    CommandPool commandPool;
    IOManager ioManager;

    public ConsoleManager(CommandPool pool, IOManager ioManager) {
        commandPool = pool;
        this.ioManager = ioManager;
    }

    public void action(String input) throws EmptyElement, IncorrectData {
        try {
            if (input.trim().isEmpty()) throw new IllegalArgumentException();
            String[] splitingInput = input.split("\\s");
            Command curCommand = commandPool.get(splitingInput[0]);
            String[] arguments = new String[Math.max(0, splitingInput.length) - 1];
            System.arraycopy(splitingInput, 1, arguments, 0, arguments.length);
            SpaceMarineArgumentLoader argumentLoader = new SpaceMarineArgumentLoader(arguments, ioManager);
            curCommand.run(argumentLoader);
        } catch (IllegalArgumentException e) {
            ioManager.printerr("Команда не найдена, воспользуйтесь командой \"help\" ");
        }

    }


}


