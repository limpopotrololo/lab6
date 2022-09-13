package Lab5.Commands;

import Lab5.Exeptions.*;

import Lab5.Utility.ArgumentLoader;
import Lab5.Utility.CollectionManager;
import Lab5.Utility.IOManager;

public class RemoveFirstCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public RemoveFirstCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }

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
