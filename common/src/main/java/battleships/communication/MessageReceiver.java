package battleships.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReceiver {
    ObjectInputStream ois;

    public MessageReceiver(ObjectInputStream ois) {
        this.ois = ois;
    }

    public String receiveMessageString() {
        String s = null;
        try {
            s = (String) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
}