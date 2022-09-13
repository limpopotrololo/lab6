package Lab5.StartingKit;

import Lab5.Commands.*;
import Lab5.Data.SpaceMarine;
import Lab5.Exeptions.*;
import Lab5.Utility.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Stack;

public class Application extends AbstractApplication {


    CommandPool commandPool = new CommandPool();
    ConsoleManager consoleManager;
    PrintWriter writer = new PrintWriter(System.out, true);
    IOManager ioManager = new IOManager(writer);

    Path path = Paths.get("src", "pars.txt");
    File file = new File(String.valueOf(path));
    Serializer serializer = new Serializer(ioManager, file);
    CollectionManager collectionManager;

    {
        try {
            collectionManager = new CollectionManager(serializer.collectionDeserializer());
        } catch (IOException e) {
            e.printStackTrace();
            ioManager.printerr("файла не существует или отсутствуют необходимые права");
        }
    }


    public Application() {
        consoleManager = new ConsoleManager(commandPool, ioManager);
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
            ioManager.printerr("Happened some shit");
        }

    }
}


