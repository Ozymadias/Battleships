package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.Messageable;
import battleships.logger.BattleshipLog;
import com.sun.nio.sctp.IllegalReceiveException;

import java.io.IOException;

/**
 * This is a wrapper class for ClientHandler. It is used to send and receive messages.
 */
public class HandlerWrapper implements Observers {

  private final ClientHandler clientHandler;
  private BattleshipLog log = BattleshipLog.provideLogger(HandlerWrapper.class);

  public HandlerWrapper(ClientHandler clientHandler) {
    this.clientHandler = clientHandler;
  }

  @Override
  public void sendMessage(Messageable messageable) {
    clientHandler.sendMessage(messageable);
  }

  @Override
  public Messageable receiveMessage() {
    try {
      return clientHandler.receiveMessage();
    } catch (ClassNotFoundException ex) {
      log.error(ex.getMessage());
    } catch (IOException ex) {
      throw new IllegalReceiveException("Client disconnect");
    }
    return null;
  }
}
