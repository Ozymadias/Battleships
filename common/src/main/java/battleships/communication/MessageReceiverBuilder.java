package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class is for building MessageReceiver object.
 * It is responsible for creating ObjectInputStream based on socket input stream
 * and creating MessageReceiver on a given ObjectInputStream.
 */
public class MessageReceiverBuilder {
  private ObjectInputStream ois;
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);

  public MessageReceiver build() {
    return new MessageReceiver(ois);
  }

  /**
   * Creates new ObjectInputStream based on input stream of a socket passed as a parameter.
   *
   * @param socket Accepts socket as a parameter.
   * @return returns this instance of builder with desired ObjectInputStream.
   */
  public MessageReceiverBuilder addSocket(Socket socket) {
    InputStream is;
    try {
      is = socket.getInputStream();
      ois = new ObjectInputStream(is);
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    return this;
  }
}
