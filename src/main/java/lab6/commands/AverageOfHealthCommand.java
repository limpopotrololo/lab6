package lab6.commands;

import lab6.exeptions.*;
import lab6.utility.ArgumentLoader;
import lab6.utility.CollectionManager;
import lab6.utility.IOManager;

/**
 * "average_health" command, print average health value in collection
 */
public class AverageOfHealthCommand extends Command {

    CollectionManager collectionManager;
    IOManager ioManager;

    public AverageOfHealthCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }


    @Override
    public void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData {
        try {
            arguments.validateCount(0);
            ioManager.println(collectionManager.getAverageHealth());

        } catch (IllegalArgumentException e) {
            ioManager.printerr("Неверное количество аргументов");
        }

    }

    @Override
    public String getName() {
        return "average_health";
    }

    @Override
    public String getDescription() {
        return "Выводит среднее значение поля health у объектов";
    }
}
