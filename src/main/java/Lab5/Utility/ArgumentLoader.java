package Lab5.Utility;

public class ArgumentLoader {
    private final String[] arguments;

    public ArgumentLoader(String[] arguments) {
        this.arguments = arguments;
    }

    public void validateCount(int count) {

        if (arguments.length != count)
            throw new IllegalArgumentException();

    }

    public String[] getStrArguments() {
        return arguments;
    }
}




