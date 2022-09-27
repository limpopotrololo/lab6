package lab.commands;

import lab.data.SpaceMarine;
import lab.exeptions.*;

import lab.utility.ArgumentLoader;
import lab.utility.CollectionManager;
import lab.utility.IOManager;

/**
 * "show" command, show whole elements from collection
 */
public class ShowCommand extends Command {

    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public ShowCommand(CollectionManager collectionManager, IOManager ioManager) {
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
            if (collectionManager.getSize() == 0) throw new IncorrectData();
            for (SpaceMarine spaceMarine : collectionManager.getCollection())
                ioManager.println(spaceMarine);
        } catch (IllegalArgumentException e) {
            ioManager.println("Неверное количество аргументов");
        } catch (IncorrectData incorrectData) {
            ioManager.println("Коллекция пуста");
        }

        //Iterator<SpaceMarine> itr = collectionManager.getCollection().iterator();
        //while(itr.hasNext()){
        //   ioManager.println(itr.next());
        // }
        //Stack<SpaceMarine> buffer = collectionManager.getCollection();
        // buffer.forEach(s -> System.out.print(s + " "));
        //for (SpaceMarine item : collectionManager.getCollection()) {
        //       ioManager.println(item);
        //
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "Выводит информацию о коллекции";
    }
}
