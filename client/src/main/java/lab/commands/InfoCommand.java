package lab.commands;


import lab.utility.ArgumentLoader;
import lab.utility.CollectionManager;
import lab.utility.IOManager;
import lab.exeptions.*;
import java.time.format.DateTimeFormatter;

/**
 * "info" command, print info about command
 */
public class InfoCommand extends Command {

    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public InfoCommand(CollectionManager collectionManager, IOManager ioManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Вывод информации о коллекции";
    }

    /**
     * execute command
     * @param arguments
     * @throws IncorrectData
     */
    @Override
    public void run(ArgumentLoader arguments) throws IncorrectData {
        try {
            arguments.validateCount(0);
            if (collectionManager.getSize() == 0) throw new IncorrectData();
            ioManager.println("Дата создания коллекции " +
                    collectionManager.getLocalDateTime().format(DateTimeFormatter.ofPattern("MMMM, dd, yyyy HH:mm:ss")) +
                    "\n" + "Размер коллекции " + collectionManager.getSize() +
                    "\n" + "Тип коллекции " + collectionManager.getCollection().getClass());
        } catch (IllegalArgumentException e) {
            ioManager.println("Неверное количество аргументов");
        } catch (IncorrectData idata) {
            ioManager.println("Коллекция пуста");
        }
    }
}
