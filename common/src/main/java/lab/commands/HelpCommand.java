package lab.commands;

import lab.data.SpaceMarine;
import lab.exeptions.EmptyElement;
import lab.exeptions.IncorrectData;
import lab.utility.ArgumentLoader;
import lab.utility.CollectionManager;
import lab.utility.CommandPool;
import lab.utility.IOManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * "help" command, print list of available command and description
 */
public class HelpCommand extends Command implements Serializable {
    private CommandResult commandResult;
    private StringBuffer buf = new StringBuffer();



    /**
     * execute command
     * @throws EmptyElement
     * @throws IncorrectData
     * @return
     */
    @Override
    public CommandResult run(CollectionManager collectionManager, Object data, SpaceMarine item) throws EmptyElement, IncorrectData {

            HashMap<String, Command> pool = collectionManager.getCommands();
            for (Map.Entry<String, Command> entry : pool.entrySet()) {
                buf.append(entry.getValue().getName() + "----" + entry.getValue().getDescription() + "\n");
            }
            return new CommandResult("help",buf, true);

    }

    public String getDescription() {
        return "Выводит список и функционал всех команд";
    }

    public String getName() {
        return "help";
    }
}
