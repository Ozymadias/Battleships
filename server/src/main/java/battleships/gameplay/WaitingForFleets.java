package battleships.gameplay;

import battleships.Observers;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.util.List;
import java.util.stream.Collectors;

class WaitingForFleets implements GameState {
  private final BattleshipLog log = BattleshipLog.provideLogger(WaitingForFleets.class);
  private final List<Observers> observers;

  WaitingForFleets(List<Observers> observers) {
    this.observers = observers;
  }

  @Override
  public GameState process() {
    log.info("Waiting for fleet");
    return new WaitingForSalvos(observers, observers
        .stream()
        .map(handlerWrapper -> (Fleet) handlerWrapper.receiveMessage())
        .collect(Collectors.toList()));
  }

  @Override
  public boolean isEndOfTheGame() {
    return false;
  }
}
