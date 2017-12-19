package battleships.communication;


import java.io.*;
import java.net.Socket;

public class MessageSenderBuilder {
    private ObjectOutputStream oos;

    public MessageSender build() {
        return new MessageSender(oos);
    }

    public MessageSenderBuilder addSocket(Socket socket) {
        OutputStream os;
        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}