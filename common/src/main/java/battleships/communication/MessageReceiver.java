package battleships.communication;

import battleships.logger.BattleshipLog;
import battleships.utils.BattleshipUtils;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageReceiver {
  private final ObjectInputStream ois;
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);

  MessageReceiver(ObjectInputStream ois) {
    this.ois = ois;
  }

  /**
   * Reads string from given ObjectInputStream.
   * @return Returns string received from ObjectInputStream.
   */
  public String receiveMessageString() {
    String s = BattleshipUtils.provideEmptyString();
    try {
      s = (String) ois.readObject();
      log.info("message send: " + s);
    } catch (IOException | ClassNotFoundException e) {
      log.error(e.getMessage());
    }
    return s;
  }
}