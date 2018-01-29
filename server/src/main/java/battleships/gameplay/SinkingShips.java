package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class SinkingShips implements GameState {
  private final List<Observers> observers;
  private final List<Fleet> playersFleets;
  private final List<SalvoResult> results;
  private static final int RESULT_MAXIMUM_INDEX = 1;

  SinkingShips(List<Observers> observers, List<Fleet> playersFleets, List<SalvoResult> results) {

    this.observers = observers;
    this.playersFleets = playersFleets;
    this.results = results;
  }

  /**
   * This method process current game state. We kill ships that are hit by salvos.
   *
   * @return next game state that is CalculatingGameResult.
   */
  @Override
  public GameState process() {
    AtomicInteger resultIndex = new AtomicInteger(RESULT_MAXIMUM_INDEX);

    playersFleets
        .forEach(p -> killShips(p.getShips(), results.get(resultIndex.getAndDecrement())));

    return new CalculatingGameResult(observers, playersFleets, results);
  }

  private void killShips(List<Ship> toBeKilled, SalvoResult toKill) {
    toBeKilled
        .forEach(p -> toKill.getResultList().forEach(p::killMast));
  }

}
