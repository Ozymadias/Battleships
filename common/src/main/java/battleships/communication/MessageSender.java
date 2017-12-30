package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MessageSender {
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
  private ObjectOutputStream oos;

  public MessageSender(ObjectOutputStream oos) {
    this.oos = oos;
  }

  /**
   * Uses ObjectOutputStream to write given message via OutputStream.
   * @param message Accepts String as a parameter.
   */
  public void sendMessageString(String message) {
    try {
      oos.writeObject(message);
      log.info("message send: " + message);
    } catch (IOException e) {
      log.error(e);
    }
  }
}
