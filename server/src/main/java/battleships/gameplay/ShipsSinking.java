package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;

import java.util.List;

class ShipsSinking implements GameState {
    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;
    private final List<SalvoResult> process;

    ShipsSinking(List<BattleObserver> observers, List<Fleet> playersFleets, List<SalvoResult> process) {

        this.observers = observers;
        this.playersFleets = playersFleets;
        this.process = process;
    }

    @Override
    public GameState process() {
        return new WaitingForSalvos(observers,playersFleets);
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
