package Lab5.Commands;

import Lab5.Exeptions.*;
import Lab5.Utility.ArgumentLoader;
import Lab5.Utility.CollectionManager;
import Lab5.Utility.IOManager;

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public RemoveByIdCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }

    @Override
    public void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData {
        try {
            arguments.validateCount(1);
            Long id = Long.parseLong(arguments.getStrArguments()[0]);
            if (collectionManager.deleteElementById(id))
                ioManager.println("Объект удален");
            else
                ioManager.println("Объекта с id = " + id + " не существует");

        } catch (IllegalArgumentException ex) {
            ioManager.println("Аргумент команды не соответствует требованиям");

        }
    }


    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "Удаляет элемент из коллекции по его id";
    }
}
