package Lab5.Commands;

import Lab5.Exeptions.*;
import Lab5.Utility.ArgumentLoader;
import Lab5.Utility.CollectionManager;
import Lab5.Utility.IOManager;

public class RemoveLowerCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public RemoveLowerCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "Удаляет все элементы коллекции меньше чем введенный";
    }

    @Override
    public void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData {
        try {
            arguments.validateCount(0);
            if (collectionManager.removeLower(arguments))
                ioManager.println("Элемент удален");
            else
                ioManager.printerr("коллекция пуста");
        } catch (IllegalArgumentException e) {
            ioManager.println("Неверное количество аргументов");
        }
    }
}
