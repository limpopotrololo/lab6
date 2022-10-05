package lab.start;

import commands.CommandResult;
import utility.MessageSerializer;

import java.io.IOException;
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
        result = (CommandResult) messageSerializer.deserialize(buf.array());
        return  result;
    }


}
