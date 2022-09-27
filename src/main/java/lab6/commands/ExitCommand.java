package lab6.commands;

import lab6.exeptions.*;
import lab6.start.AbstractApplication;
import lab6.utility.ArgumentLoader;
import lab6.utility.IOManager;

/**
 * "exite" command, end of work program
 */
public class ExitCommand extends Command {
    IOManager ioManager;

    public ExitCommand(IOManager ioManager) {
        this.ioManager = ioManager;
    }

    /**
     * execute command
     * @param arguments
     * @throws EmptyElement
     * @throws IncorrectData
     */
    public void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData {
        try {
            arguments.validateCount(0);
            ioManager.println("_____Работа программы завершена_____");
            AbstractApplication.onStop();
        } catch (IllegalArgumentException e) {
            ioManager.printerr("Неверное количество аргументов");
        }


    }

    public String getName() {
        return "exit";
    }

    public String getDescription() {
        return "Завершение работы программы...";
    }
}
