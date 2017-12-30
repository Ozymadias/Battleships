package battleships;

import battleships.communication.Messagable;

public interface Observers {
  void sendMessage(Messagable messagable);

  Messagable receiveMessage();
}
