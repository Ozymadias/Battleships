package battleships;

import battleships.communication.Messagable;

public interface BattleObserver {
    void getNotified(Messagable messagable);
    Messagable raport();
}
