package battleships.communication;

import battleships.logger.BattleshipLog;
import battleships.utils.BattleshipUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageReceiver {
    private final ObjectInputStream ois;
    private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
    public MessageReceiver(ObjectInputStream ois) {
        this.ois = ois;
    }

    public String receiveMessageString() {
        String s = BattleshipUtils.provideEmptyString();
        try {
            s = (String) ois.readObject();
            log.info("message send: " + s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return s;
    }
}