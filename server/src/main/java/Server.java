import commands.*;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import utility.CollectionManager;
import utility.CommandPool;

import java.net.*;
import java.util.Objects;

public class Server {

    private static final Integer PORT = 4587;
    private static InetAddress addr;

    public static void main(String[] args) throws SocketException, UnknownHostException, EmptyElement, IncorrectData {
        CommandPool commandPool = new CommandPool();
        CollectionManager collectionManager = new CollectionManager(commandPool);
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
        ServerManager serverManager = new ServerManager(commandPool,getHost(addr) ,PORT);
        serverManager.run();
    }

    public static InetAddress getHost(InetAddress inetAddress) throws UnknownHostException {
        try {
            if (Objects.equals(System.getenv("host"), null)) {
                return InetAddress.getLocalHost();
            } else
                return InetAddress.getByName(System.getenv("host"));
        } catch (UnknownHostException e) {
            return InetAddress.getLocalHost();
        }
    }

}
