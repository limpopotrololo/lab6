package commands;

import data.SpaceMarine;
import exeptions.IncorrectData;
import exeptions.EmptyElement;
import utility.CollectionManager;

/**
 * "update" command, update collection item by id
 */
public class UpdateIdCommand extends Command {

    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine newSpaceMarine) throws EmptyElement, IncorrectData {
            if (data instanceof Number){
                if (collectionManager.getSize() == 0)
                    return new CommandResult("update", "коллекция пуста",false);
                else {
                    SpaceMarine findMarine = collectionManager.findElementById(((Number) data).longValue());
                    if (findMarine == null)
                        return new CommandResult("update", "в коллекции нет элемента с данным id",false);
                    else {
                        collectionManager.addMarine(newSpaceMarine);
                        return new CommandResult("update", "____Элемент обновлен____",true);
                    }
                }
            } else
                return new CommandResult("update", "Неверный формат id",false);
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Обновляет элемент по введенному id";
    }
}
