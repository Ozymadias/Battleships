package battleships;

import battleships.communication.Messagable;

import java.util.List;
import java.util.stream.Collectors;

public class WaitForFleets implements GameState {

    private final List<HandlerWrapper> observers;

    WaitForFleets(List<HandlerWrapper> observers) {
        this.observers = observers;
    }

    @Override
    public GameState process() {
        return new GameInProgress(observers, observers
                .stream()
                .map(HandlerWrapper::raport)
                .collect(Collectors.toList()));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
