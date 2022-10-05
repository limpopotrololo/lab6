package lab.utility;

import commands.Command;
import commands.CommandResult;
import exeptions.EmptyElement;
import exeptions.IncorrectData;
import lab.start.ReceiveManager;
import lab.start.SendManager;
import utility.CommandPool;
import utility.IOManager;
import utility.Message;
import utility.SpaceMarineArgumentLoader;

import java.io.IOException;

/**
 * Class for separating input and pick approach command
 */

public class ConsoleManager {
    utility.CommandPool commandPool;
    utility.IOManager ioManager;
    Message message = new Message();
    SendManager sendManager;
    ReceiveManager receiveManager;

    public ConsoleManager(CommandPool pool, IOManager ioManager, SendManager sendManager, ReceiveManager receiveManager) {
        commandPool = pool;
        this.ioManager = ioManager;
        this.sendManager = sendManager;
        this.receiveManager = receiveManager;
    }

    public void action(String input) throws EmptyElement, IncorrectData {
        try {
            String[] splitingInput = input.split("\\s");
            if (input.trim().isEmpty() || splitingInput.length>=2) throw new IllegalArgumentException();
            Command curCommand = commandPool.get(splitingInput[0]);
            String[] arguments = new String[Math.max(0, splitingInput.length) - 1];
            System.arraycopy(splitingInput, 1, arguments, 0, arguments.length);
            utility.SpaceMarineArgumentLoader argumentLoader = new SpaceMarineArgumentLoader(arguments, ioManager);
            message.loadPreMessage(curCommand, splitingInput);
            sendManager.sendMessage(message);
            CommandResult result =  receiveManager.receiveMessage();
            System.out.println(result.getResult());


            //curCommand.run(argumentLoader);
        } catch (IllegalArgumentException e) {
            ioManager.printerr("Команда не найдена, воспользуйтесь командой \"help\" ");
        }catch (IOException e) {
            ioManager.printerr("Какая-то хуйня с сервером");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //never throw
        }


    }


}


