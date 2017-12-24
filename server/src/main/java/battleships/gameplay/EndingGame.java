package battleships.gameplay;

import battleships.BattleObserver;
import battleships.ships.Fleet;

import java.util.List;

class EndingGame implements GameState {

    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;

    EndingGame(List<BattleObserver> observers, List<Fleet> playersFleets) {
        this.observers = observers;
        this.playersFleets = playersFleets;
    }

    public GameState process() {
        return this;
    }

    public boolean isEndOfTheGame() {
        return true;
    }
}
