package battleships;

import battleships.ships.Fleet;

import java.util.List;

public class EndOfTheGame implements GameState {

    private final List<BattleObserver> observers;
    private final List<Fleet> playersFleets;

    EndOfTheGame(List<BattleObserver> observers, List<Fleet> playersFleets) {
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
