package lab5.commands;

import lab5.exeptions.*;
import lab5.utility.ArgumentLoader;
import lab5.utility.CollectionManager;
import lab5.utility.ConsoleManager;
import lab5.utility.IOManager;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Stack;

/**
 * "execute" command, script execution
 */
public class ExecuteScriptCommand extends Command {

    private final IOManager ioManager;
    private final CollectionManager collectionManager;
    private final ConsoleManager consoleManager;
    private final Stack<File> recursionFile = new Stack<>();

    public ExecuteScriptCommand(CollectionManager collectionManager, IOManager ioManager, ConsoleManager consoleManager) {
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
        this.consoleManager = consoleManager;
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
            arguments.validateCount(1);
            String fileName = arguments.getStrArguments()[0];
            Path path = Paths.get("src", fileName);
            File file = new File(String.valueOf(path));
            Boolean state = true;
            String input;
            BufferedReader filesReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            if (file.exists() && !recursionFile.contains(file)) {
                ioManager.println("Начинается исполнение файла: " + file.getName());
                recursionFile.push(file);
                while (state) {
                    input = filesReader.readLine();
                    if (!Objects.equals(input, null)) {
                        consoleManager.action(input);
                        ioManager.println("-----------------------------");
                    } else {
                        state = false;
                    }
                }
                ioManager.println("Исполнение файла закончено");
                recursionFile.pop();
            } else if (!file.exists()) {
                ioManager.printerr("файла не существует");
            } else if (recursionFile.contains(file)) {
                ioManager.printerr("файл не будет исполняться, для избежания рекурсивных вызовов");
            }

        } catch (IOException e) {
            e.printStackTrace();
            ioManager.printerr("файла либо не существует, либо отсутствуют необходимые права");
        } catch (IllegalArgumentException e) {
            ioManager.printerr("неверное количество аргументов");
        }
    }

    @Override
    public String getDescription() {
        return "Выполняет скрипт из файла";
    }

    @Override
    public String getName() {
        return "execute";
    }
}
