package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.Salvo;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.util.List;
import java.util.stream.Collectors;

class WaitingForSalvos implements GameState {

  private final List<BattleObserver> observers;
  private final List<Fleet> playersFleets;
  private final BattleshipLog log = BattleshipLog.provideLogger(WaitingForSalvos.class);

  WaitingForSalvos(List<BattleObserver> observers, List<Fleet> playersFleets) {
    this.observers = observers;
    this.playersFleets = playersFleets;
  }

  @Override
  public GameState process() {
    log.info("Waiting for salvos");
    List<Salvo> salvos = observers.stream().map(p -> (Salvo) p.receiveMessage())
        .collect(Collectors.toList());
    return new SalvosProcessing(observers, playersFleets, salvos);
  }

  @Override
  public boolean isEndOfTheGame() {
    return false;
  }
}
