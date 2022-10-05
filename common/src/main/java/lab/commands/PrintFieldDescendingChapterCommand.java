package lab.commands;

import lab.data.SpaceMarine;
import lab.exeptions.EmptyElement;
import lab.exeptions.IncorrectData;
import lab.data.Chapter;
import lab.utility.ArgumentLoader;
import lab.utility.CollectionManager;
import lab.utility.IOManager;
import lab.utility.SpaceMarineArgumentLoader;

/**
 * "print_chapter" command,  Print descending chapter's fields
 */
public class PrintFieldDescendingChapterCommand extends Command {
    private StringBuffer buf = new StringBuffer();

    /**
     * execute command
     *
     * @return
     * @throws EmptyElement
     * @throws IncorrectData
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {

        if (collectionManager.getSize() == 0)
            return new CommandResult("print_chapter", "Коллекция пуста", false);

        for (Chapter chapter : collectionManager.printChapterFields().descendingMap().values())
            buf.append(chapter + "\n");
        return new CommandResult("print_chapter", buf, true);
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
