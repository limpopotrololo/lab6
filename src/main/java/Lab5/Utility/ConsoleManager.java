package Lab5.Utility;

import Lab5.Commands.Command;
import Lab5.Exeptions.*;


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


