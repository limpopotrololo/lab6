package Lab5.StartingKit;



public class Start {
    public static void main(String[] args) {

        try {
            new Application().start();
        } catch (Throwable e) {
            e.printStackTrace(); // never throw
        }

    }
}

