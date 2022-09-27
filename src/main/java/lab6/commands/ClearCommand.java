package lab6.commands;

import lab6.exeptions.*;
import lab6.utility.ArgumentLoader;
import lab6.utility.CollectionManager;
import lab6.utility.IOManager;

/**
 * "clear" command, clear whole collection
 */
public class ClearCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public ClearCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
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
            collectionManager.clearCollection();
            ioManager.println("Коллекция пуста, в ней удалены все элементы");
        } catch (IllegalArgumentException e) {
            ioManager.println("Неверное количество аргументов");
        }


    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы коллекции";
    }
}
