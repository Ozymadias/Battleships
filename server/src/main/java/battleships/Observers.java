package battleships;

import battleships.communication.Messageable;

/**
 * This interface is implemented by classes that can send and receive messages.
 */
public interface Observers {
  /**
   * It sends message using underlying implementation.
   * @param messageable message to be send.
   */
  void sendMessage(Messageable messageable);

  /**
   * It receives message using underlying implementation.
   * @return received Messagable object.
   */
  Messageable receiveMessage();
}
