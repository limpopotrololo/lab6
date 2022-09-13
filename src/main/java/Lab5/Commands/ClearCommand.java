package Lab5.Commands;

import Lab5.Exeptions.*;
import Lab5.Utility.ArgumentLoader;
import Lab5.Utility.CollectionManager;
import Lab5.Utility.IOManager;

public class ClearCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public ClearCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;

    }

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
