package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;

import java.util.List;

class SendingSalvoResults implements GameState {
    SendingSalvoResults(List<BattleObserver> observers, List<Fleet> playersFleets, List<SalvoResult> process) {

    }

    @Override
    public GameState process() {
        return null;
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
