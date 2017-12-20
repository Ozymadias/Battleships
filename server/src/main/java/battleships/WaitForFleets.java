package battleships;

import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.util.List;
import java.util.stream.Collectors;

public class WaitForFleets implements GameState {
    private final BattleshipLog log = BattleshipLog.provideLogger(WaitForFleets.class);
    private final List<HandlerWrapper> observers;

    WaitForFleets(List<HandlerWrapper> observers) {
        this.observers = observers;
    }

    @Override
    public GameState process() {
        log.info("Waiting for fleet");
        return new GameInProgress(observers, observers
                .stream()
                .map(handlerWrapper -> (Fleet) handlerWrapper.raport())
                .collect(Collectors.toList()));
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
