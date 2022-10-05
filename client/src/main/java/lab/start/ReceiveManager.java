package lab.start;

import lab.commands.CommandResult;
import lab.utility.Message;
import lab.utility.MessageSerializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ReceiveManager {
    private DatagramChannel channel;
    private SocketAddress addr;
    private CommandResult result;
    MessageSerializer messageSerializer;

    public ReceiveManager(DatagramChannel channel, MessageSerializer messageSerializer) {
        this.channel = channel;
        this.messageSerializer = messageSerializer;
    }

    public CommandResult receiveMessage() throws IOException, ClassNotFoundException {
        ByteBuffer buf = ByteBuffer.allocate(10000);
        while (buf.position()==0) {
            addr = channel.receive(buf);
        }
        //buf.flip();
        System.out.println(buf.position());
        System.out.println(buf);
        result = (CommandResult) messageSerializer.deserialize(buf.array());
        return  result;
    }


}
