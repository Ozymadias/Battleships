package battleships;

import battleships.communication.messages.GoodByeMessage;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.List;

public class EndOfTheGame implements GameState {

    private final List<HandlerWrapper> observers;
    private final List<Fleet> playersFleets;

    EndOfTheGame(List<HandlerWrapper> observers, List<Fleet> playersFleets) {
        this.observers = observers;
        this.playersFleets = playersFleets;
    }

    public GameState process() {
        if (playersFleets.get(0).getShips().stream().allMatch(Ship::isSunk)) {
            observers.get(0).getNotified(new GoodByeMessage("Przegrales"));
            observers.get(1).getNotified(new GoodByeMessage("Wygrales"));
        } else if (playersFleets.get(1).getShips().stream().allMatch(Ship::isSunk)) {
            observers.get(0).getNotified(new GoodByeMessage("Wygrales"));
            observers.get(1).getNotified(new GoodByeMessage("Przegrales"));
        }
        return this;
    }

    public boolean isEndOfTheGame() {
        return true;
    }
}
