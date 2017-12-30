package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MessageSenderBuilder {
  private ObjectOutputStream oos;
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);

  public MessageSender build() {
    return new MessageSender(oos);
  }

  /**
   * Creates new ObjectOutputSteam created with socket OutputStream as a parameter.
   * @param socket Accepts socket as a parameter.
   * @return returns this instance of builder with desired ObjectOutputStream.
   */
  public MessageSenderBuilder addSocket(Socket socket) {
    OutputStream os;
    try {
      os = socket.getOutputStream();
      oos = new ObjectOutputStream(os);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return this;
  }
}