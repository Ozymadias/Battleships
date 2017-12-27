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

  public void sendMessageString(String m) {
    try {
      oos.writeObject(m);
      log.info("message send: " + m);
    } catch (IOException e) {
      log.error(e);
    }
  }
}
