package battleships.communication;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReceiverBuilder {
    ObjectInputStream ois;

    public MessageReceiver build() {
        return new MessageReceiver(ois);
    }

    public MessageReceiverBuilder addSocket(Socket socket) {
        InputStream is;
        try {
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
