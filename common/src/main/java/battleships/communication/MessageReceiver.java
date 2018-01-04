package battleships.communication;

import battleships.logger.BattleshipLog;
import battleships.utils.BattleshipUtils;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class is used to read from a given ObjectInputStream to receive object in Json format.
 */
public class MessageReceiver {
  private final ObjectInputStream ois;
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);

  MessageReceiver(ObjectInputStream ois) {
    this.ois = ois;
  }

  /**
   * Reads string from given ObjectInputStream.
   *
   * @return Returns string received from ObjectInputStream.
   */
  public String receiveMessageString() {
    String receivedString = BattleshipUtils.provideEmptyString();
    try {
      receivedString = (String) ois.readObject();
      log.info("message received: " + receivedString);
    } catch (IOException | ClassNotFoundException e) {
      log.error(e.getMessage());
    }
    return receivedString;
  }
}