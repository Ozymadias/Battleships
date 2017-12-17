package battleships.communication;

import java.net.Socket;

public class ClientHandlerBuilder {
    MessageReceiver messageReceiver;
    MessageSender messageSender;
    Socket socket;

    public ClientHandlerBuilder setSocket(Socket socket) {
        this.socket = socket;
        return this;
    }

    public ClientHandlerBuilder addMessageReceiver() {
        messageReceiver = new MessageReceiverBuilder().addSocket(socket).build();
        return this;
    }

    public ClientHandlerBuilder addMessageSender() {
        messageSender = new MessageSenderBuilder().addSocket(socket).build();
        return this;
    }

    public ClientHandler build() {
        return new ClientHandler(messageSender, messageReceiver);
    }
}
