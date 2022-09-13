package Lab5.Exeptions;

public class EmptyElement extends Exception {
    public EmptyElement() {
        super("Can't be null");
    }

}
