package lab.start;


import commands.*;
import data.SpaceMarine;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import lab.utility.ConsoleManager;
import utility.CollectionManager;
import utility.CollectionSerializer;
import utility.CommandPool;
import utility.MessageSerializer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

/**
 * Main application class that loads all commands and initializes instances
 *
 * @author Ri Arkadiy
 * @throws IncorrectData never throws
 * @throws IOException  when something with file went wrong
 * @throws IncorrectData never throws
 */


public class Client extends AbstractClient {

    private final static Integer TIME_OUT = 1000;
    private final static Integer DEFAULT_PORT = 4587;
    utility.CommandPool commandPool = new CommandPool();
    ConsoleManager consoleManager;
    PrintWriter writer = new PrintWriter(System.out, true);
    utility.IOManager ioManager = new utility.IOManager(writer);
    MessageSerializer messageSerializer;
    CollectionSerializer collectionSerializer;
    CollectionManager collectionManager;
    DatagramChannel client;
    InetAddress host;
    InetSocketAddress serverAddr;
    SendManager sendManager;
    ReceiveManager receiveManager;
    String path;

    public Client() throws IncorrectData {
        messageSerializer = new MessageSerializer();
        setPath();
        setConnectionStuff();
        File file = new File(path);
        collectionSerializer = new CollectionSerializer(ioManager, file);
        consoleManager = new ConsoleManager(commandPool, ioManager, sendManager, receiveManager);
        setCollectionManager();
    }

    @Override
    public void onStart() {
        ioManager.println("______Программа готова к работе_____");
        super.onStart();
        collectionManager.startSetId(); //устанавливает множество id
        commandPool.upload(new AddCommand());
        commandPool.upload(new HelpCommand());
        commandPool.upload(new InfoCommand());
        commandPool.upload(new ShowCommand());
        commandPool.upload(new AddIfMaxCommand());
        commandPool.upload(new AverageOfHealthCommand());
        commandPool.upload(new ClearCommand());
        commandPool.upload(new RemoveByIdCommand());
        commandPool.upload(new RemoveFirstCommand());
        commandPool.upload(new PrintUniqueHealthCommand());
        commandPool.upload(new RemoveLowerCommand());
        commandPool.upload(new PrintFieldDescendingChapterCommand());
        commandPool.upload(new ExitCommand());
        commandPool.upload(new UpdateIdCommand());
    }

    public static void onStop() {
        AbstractClient.onStop();
    }

    @Override
    public void run()  {
        try {
            String input = new Scanner(System.in).nextLine();
            consoleManager.action(input);
        } catch (IllegalArgumentException | EmptyElement | IncorrectData | InterruptedException e) {
            ioManager.printerr("Happened some shit"); //never throw
        }
    }

    public void setPath() throws IncorrectData{
        path = System.getenv("parsPath");
        if (Objects.equals(path, null)) {
            ioManager.printerr("файл не обнаружен");
            throw new IncorrectData();
        }
    }

    public void setCollectionManager(){
        try {
            collectionManager = new CollectionManager(commandPool, collectionSerializer, collectionSerializer.collectionDeserializer());
        } catch (IOException e) {
            e.printStackTrace();
            ioManager.printerr("файла не существует или отсутствуют необходимые права");
        }
    }

    public void setConnectionStuff() {
        try {
            serverAddr = new InetSocketAddress(getHost(host), DEFAULT_PORT);
            client = DatagramChannel.open();
            client.configureBlocking(false);
            sendManager = new SendManager(client, serverAddr,messageSerializer);
            receiveManager = new ReceiveManager(client, messageSerializer);
        } catch (UnknownHostException e) {
            e.printStackTrace(); //never throw
        } catch (IOException e) {
            e.printStackTrace(); //never throw
        }
    }

    public InetAddress getHost(InetAddress inetAddress) throws UnknownHostException {
        try {
            if (Objects.equals(System.getenv("host"), null)) {
                return InetAddress.getLocalHost();
            } else
                return InetAddress.getByName(System.getenv("host"));
        } catch (UnknownHostException e) {
            ioManager.printerr("Unknown host" + "\n" + "Set local host");
        }
        return InetAddress.getLocalHost();
    }

}


