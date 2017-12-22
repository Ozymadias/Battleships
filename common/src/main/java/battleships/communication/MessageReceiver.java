package battleships.communication;

import battleships.utils.BattleshipUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReceiver {
    private final ObjectInputStream ois;
    public MessageReceiver(ObjectInputStream ois) {
        this.ois = ois;
    }

    public String receiveMessageString() {
        String s = BattleshipUtils.provideEmptyString();
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