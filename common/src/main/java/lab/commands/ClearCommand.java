package lab.commands;

import lab.data.SpaceMarine;
import lab.exeptions.EmptyElement;
import lab.exeptions.IncorrectData;
import lab.utility.ArgumentLoader;
import lab.utility.CollectionManager;
import lab.utility.IOManager;
import lab.utility.SpaceMarineArgumentLoader;

/**
 * "clear" command, clear whole collection
 */
public class ClearCommand extends Command {


    /**
     * execute command
     *
     * @return
     * @throws EmptyElement
     * @throws IncorrectData
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {

        collectionManager.clearCollection();
        return new CommandResult("clear", "Коллекция пуста, в ней удалены все элементы", true);

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
