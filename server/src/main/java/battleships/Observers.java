package battleships;

import battleships.communication.Messageable;

public interface Observers {
  void sendMessage(Messageable messageable);

  Messageable receiveMessage();
}
