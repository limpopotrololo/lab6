package lab6.commands;

import lab6.exeptions.*;
import lab6.utility.ArgumentLoader;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    private String name;
    private String description;

    public abstract void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData;

    /**
     * @return set command name
     */
    public String getName() {
        return name;
    }

    /**
     * @return command description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " --- " + description;


    }
}
