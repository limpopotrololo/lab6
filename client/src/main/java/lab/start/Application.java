package lab.start;


import lab.commands.*;
import lab.exeptions.*;
import lab.utility.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/** Main application class that loads all commands and initializes instances
 * @author Ri Arkadiy
 * @throws IncorrectData never throws
 * @throws IOException  when something with file went wrong
 * @throws IncorrectData never throws
 */


public class Application extends AbstractApplication {


    CommandPool commandPool = new CommandPool();
    ConsoleManager consoleManager;
    PrintWriter writer = new PrintWriter(System.out, true);
    IOManager ioManager = new IOManager(writer);

    //Path path = Paths.get("src", "pars.txt");
    //System.getenv(pars);
  //  File file = new File(String.valueOf(path));


    Serializer serializer;
    CollectionManager collectionManager;



    public Application() throws IncorrectData {
        String path = System.getenv("parsPath");

        if (Objects.equals(path, null)){
            ioManager.printerr("файл не обнаружен");
            throw new IncorrectData();
        }
        File file = new File(path);
        serializer = new Serializer(ioManager, file);
        consoleManager = new ConsoleManager(commandPool, ioManager);
        try {
            collectionManager = new CollectionManager(serializer.collectionDeserializer());
        } catch (IOException e) {
            e.printStackTrace();
            ioManager.printerr("файла не существует или отсутствуют необходимые права");
        }
    }

    @Override
    public void onStart() {
        ioManager.println("______Программа готова к работе_____");
        super.onStart();
        collectionManager.startSetId(); //устанавливает множество id
        commandPool.upload(new AddCommand(collectionManager, ioManager));
        commandPool.upload(new HelpCommand(commandPool, ioManager));
        commandPool.upload(new ExitCommand(ioManager));
        commandPool.upload(new InfoCommand(collectionManager, ioManager));
        commandPool.upload(new ShowCommand(collectionManager, ioManager));
        commandPool.upload(new AddIfMaxCommand(collectionManager, ioManager));
        commandPool.upload(new AverageOfHealthCommand(collectionManager, ioManager));
        commandPool.upload(new ClearCommand(collectionManager, ioManager));
        commandPool.upload(new RemoveByIdCommand(collectionManager, ioManager));
        commandPool.upload(new RemoveFirstCommand(collectionManager, ioManager));
        commandPool.upload(new PrintUniqueHealthCommand(collectionManager, ioManager));
        commandPool.upload(new RemoveLowerCommand(collectionManager, ioManager));
        commandPool.upload(new PrintFieldDescendingChapterCommand(collectionManager, ioManager));
        commandPool.upload(new ExecuteScriptCommand(collectionManager, ioManager, consoleManager));
        commandPool.upload(new SaveCommand(collectionManager, ioManager, serializer));
        commandPool.upload(new UpdateIdCommand(collectionManager, ioManager));
    }


    public static void onStop() {
        AbstractApplication.onStop();

    }

    @Override
    public void run() throws EmptyElement, IncorrectData {


        try {
            String input = new Scanner(System.in).nextLine();
            consoleManager.action(input);
        } catch (IllegalArgumentException | EmptyElement | IncorrectData e) {
            ioManager.printerr("Happened some shit"); //never throw
        }

    }
}


