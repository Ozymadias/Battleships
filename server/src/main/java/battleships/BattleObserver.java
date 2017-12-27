package battleships;

import battleships.communication.Messagable;

public interface BattleObserver {
  void sendMessage(Messagable messagable);

  Messagable receiveMessage();
}
