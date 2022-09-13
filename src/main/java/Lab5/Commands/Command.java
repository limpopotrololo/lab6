package Lab5.Commands;

import Lab5.Exeptions.*;
import Lab5.Utility.ArgumentLoader;

public abstract class Command {
    private String name;
    private String description;

    public abstract void run(ArgumentLoader arguments) throws EmptyElement, IncorrectData;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " --- " + description;


    }
}
