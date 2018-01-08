package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.Messageable;

/**
 * This is a wrapper class for ClientHandler. It is used to send and receive messages.
 */
public class HandlerWrapper implements Observers {
  private final ClientHandler clientHandler;

  public HandlerWrapper(ClientHandler clientHandler) {
    this.clientHandler = clientHandler;
  }

  @Override
  public void sendMessage(Messageable messageable) {
    clientHandler.sendMessage(messageable);
  }

  @Override
  public Messageable receiveMessage() {
    return clientHandler.receiveMessage();
  }
}
