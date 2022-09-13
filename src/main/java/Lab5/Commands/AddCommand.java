package Lab5.Commands;

import Lab5.Data.SpaceMarine;
import Lab5.Exeptions.*;
import Lab5.Utility.*;
import java.util.Date;

/**
 * "add" command, add SpaceMarine instance in collection
 */
public class AddCommand extends Command {
    CollectionManager collectionManager;
    IOManager ioManager;

    public AddCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }

    /**
     * execute command
     * @param collectionManager
     * @param ioManager
     */
    @Override
    public void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData {
        try {
            arguments.validateCount(0);
            if (!(arguments instanceof SpaceMarineArgumentLoader)) throw new IllegalArgumentException();
            SpaceMarine item = ((SpaceMarineArgumentLoader) arguments).loadSpaceMarin();
            Date date = new Date();
            item.setCreationDate(date);
            collectionManager.addMarine(item);
            ioManager.println("Объект создан успешно!");
        } catch (IllegalArgumentException e) {
            ioManager.println("Неверное количество аргументов");
        }


    }

    @Override
    public String getDescription() {
        return "Добавляет новый объект класса SpaceMarine в коллекцию";
    }

    public String getName() {
        return "add";
    }
}
