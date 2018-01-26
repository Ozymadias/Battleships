package battleships.communication;

import battleships.logger.BattleshipLog;

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
   * @return string received from ObjectInputStream
   */
  //I've decided to throw exception to be able to proper handle it in upper layers
  public String receiveMessageString() throws IOException, ClassNotFoundException {
    String receivedString = (String) ois.readObject();
    log.info("message received: " + receivedString);
    return receivedString;
  }
}