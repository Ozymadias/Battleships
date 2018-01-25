package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is used to write String message to the given ObjectOutputStream.
 */
public class MessageSender {
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
  private ObjectOutputStream oos;

  public MessageSender(ObjectOutputStream oos) {
    this.oos = oos;
  }

  /**
   * Writes a given String message to the ObjectOutputStream.
   *
   * @param message Accepts String as a parameter.
   */
  public void sendMessageString(String message) {
    try {
      oos.writeObject(message);
      log.info("message send: " + message);
    } catch (IOException ex) {
      log.error(ex);
    }
  }
}
