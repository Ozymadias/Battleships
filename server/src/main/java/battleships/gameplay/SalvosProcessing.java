package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.util.List;

class SalvosProcessing implements GameState {


  private final BattleshipLog log = BattleshipLog.provideLogger(SalvosProcessing.class);
  private final List<Observers> observers;
  private final List<Fleet> playersFleets;
  private final List<Salvo> salvos;

  SalvosProcessing(List<Observers> observers, List<Fleet> playersFleets, List<Salvo> salvos) {
    this.observers = observers;
    this.playersFleets = playersFleets;
    this.salvos = salvos;
  }

  @Override
  public GameState process() {
    log.info("processing salvos");
    List<SalvoResult> resultList = new SalvoProcessor().process(salvos, playersFleets);
    return new SinkingShips(observers, playersFleets, resultList);
  }

  @Override
  public boolean isEndOfTheGame() {
    return false;
  }
}
