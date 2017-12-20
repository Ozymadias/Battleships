package battleships.controller.communication;

import battleships.communication.MessageReceiver;
import battleships.communication.MessageReceiverBuilder;
import battleships.communication.MessageSender;
import battleships.communication.MessageSenderBuilder;

import java.io.IOException;
import java.net.Socket;

public class ServerLoginManager {
    private final MessageSender messageSender;
    private final MessageReceiver messageReceiver;
    private final Socket defaultSocket;

    public ServerLoginManager(String ip, String port) throws IOException {
        defaultSocket = new Socket("localhost", Integer.parseInt(port));
        messageSender = new MessageSenderBuilder().addSocket(defaultSocket).build();
        messageReceiver = new MessageReceiverBuilder().addSocket(defaultSocket).build();
    }
}
