package battleships.communication;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender {
    ObjectOutputStream oos;

    public MessageSender(ObjectOutputStream oos) {
        this.oos = oos;
    }
    public void sendMessageString(String m) {
        try {
            oos.writeObject(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
