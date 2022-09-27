package lab.commands;

import lab.data.Chapter;
import lab.exeptions.*;
import lab.utility.ArgumentLoader;
import lab.utility.CollectionManager;
import lab.utility.IOManager;

/**
 * "print_chapter" command,  Print descending chapter's fields
 */
public class PrintFieldDescendingChapterCommand extends Command {
    private final CollectionManager collectionManager;
    private final IOManager ioManager;

    public PrintFieldDescendingChapterCommand(CollectionManager collectionManager, IOManager ioManager) {
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
            for (Chapter chapter : collectionManager.printChapterFields().descendingMap().values())
                //ioManager.printerr(collectionManager.printChapterFields());
                ioManager.println(chapter);

        } catch (IllegalArgumentException e) {
            ioManager.printerr("Неверное количество аргументов");
        } catch (EmptyElement emptyElement) {
            ioManager.printerr("коллекция пуста");
        }

    }

    @Override
    public String getName() {
        return "print_chapter";
    }

    @Override
    public String getDescription() {
        return "Выводит поля Chapter всех элементов коллекции в порядке убывания";
    }
}
