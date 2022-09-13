package Lab5.Commands;

import Lab5.Exeptions.*;
import Lab5.Utility.ArgumentLoader;
import Lab5.Utility.CollectionManager;
import Lab5.Utility.IOManager;
import Lab5.Utility.Serializer;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * "save" command, save whole collection in file (json format)
 */
public class SaveCommand extends Command {
    CollectionManager collectionManager;
    IOManager ioManager;
    Serializer serializer;

    public SaveCommand(CollectionManager collectionManager, IOManager ioManager, Serializer serializer) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
        this.serializer = serializer;

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
            if (collectionManager.getCollection().isEmpty()) throw new EmptyElement();
            serializer.collectionSerializer(collectionManager.getCollection());
        } catch (JsonProcessingException e) {
            ioManager.printerr("файла либо не существует, либо отсутствуют необходимые права");
        } catch (EmptyElement e) {
            ioManager.printerr("коллекция пуста");
        }

    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "Сохраняет информацию о коллекции в файл";
    }
}
