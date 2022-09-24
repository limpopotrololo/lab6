package lab5.commands;

import lab5.exeptions.*;

import lab5.utility.ArgumentLoader;
import lab5.utility.CollectionManager;
import lab5.utility.IOManager;

/**
 * "remove_first" command, remove first element in collection
 */
public class RemoveFirstCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public RemoveFirstCommand(CollectionManager collectionManager, IOManager ioManager) {
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
            if (collectionManager.removeFirstElement())
                ioManager.println("Первый элемент удален");
            else {
                ioManager.println("Коллекция пуста");
            }
        } catch (IllegalArgumentException e) {
            ioManager.println("Неверное количество аргументов");
        }


    }

    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return "Удаляет первый элемент коллекции";
    }
}
