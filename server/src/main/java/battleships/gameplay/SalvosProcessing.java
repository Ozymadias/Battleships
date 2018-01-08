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

  /**
   * This method process current game state. We process salvos send by both clients. The result of this
   * processing is SalvoResult list that is passed to the next game state. SalvoResult list is calculated based on
   * player's fleet positions and salvos send by both players.
   *
   * @return next game state that is SinkingShips.
   */
  @Override
  public GameState process() {
    log.info("processing salvos");
    List<SalvoResult> resultList = new SalvoProcessor().process(salvos, playersFleets);
    return new SinkingShips(observers, playersFleets, resultList);
  }

  /**
   * This game state is not ending the game.
   */
  @Override
  public boolean isEndOfTheGame() {
    return false;
  }
}
