package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * This class is for building MessageSender object.
 * It is responsible for creating ObjectOutputStream based on socket output stream
 * and creating MessageSender on a given ObjectOutputStream.
 */
class MessageSenderBuilder {
  private ObjectOutputStream oos;
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);

  public MessageSender build() {
    return new MessageSender(oos);
  }

  /**
   * Creates new ObjectOutputSteam created with socket OutputStream as a parameter.
   *
   * @param socket Accepts socket as a parameter.
   * @return returns this instance of builder with desired ObjectOutputStream.
   */
  MessageSenderBuilder addSocket(Socket socket) {
    OutputStream os;
    try {
      os = socket.getOutputStream();
      oos = new ObjectOutputStream(os);
    } catch (IOException ex) {
      log.error(ex.getMessage());
    }
    return this;
  }
}