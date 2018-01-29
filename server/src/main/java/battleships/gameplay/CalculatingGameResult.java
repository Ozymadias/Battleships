package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.SalvoResult;
import battleships.game.GameResult;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CalculatingGameResult implements GameState {
  private final BattleshipLog log = BattleshipLog.provideLogger(CalculatingGameResult.class);
  private final List<Observers> observers;
  private final List<Fleet> fleets;
  private final List<SalvoResult> results;

  CalculatingGameResult(List<Observers> observers, List<Fleet> fleets, List<SalvoResult> results) {
    this.observers = observers;
    this.fleets = fleets;
    this.results = results;
  }

  /**
   * This method process current game state. We decide if last salvo causes end of the game.
   * We calculate game results and set game results for SalvoResult message for each player.
   * @return next game state that is SendingSalvoResults.
   */
  @Override
  public GameState process() {
    log.info("processing game result");
    boolean isEndOfTheGame = calculateGameResult(fleets, results);
    return new SendingSalvoResults(observers, fleets, results, isEndOfTheGame);
  }

  private boolean calculateGameResult(List<Fleet> playersFleets, List<SalvoResult> results) {

    List<Fleet> sunkenFleets = playersFleets.stream()
        .filter(this::isFleetSunk)
        .collect(Collectors.toList());

    if (sunkenFleets.size() == 2) {
      results.forEach(p -> p.setGameResult(GameResult.DRAW));
      log.info("We have a draw");
      return true;

    } else if (sunkenFleets.size() == 1) {
      assignLoser(playersFleets, results);
      assignWinner(playersFleets, results);
      log.info("We have winner and looser");
      return true;

    }
    log.info("Game continue");
    return false;
  }

  private void assignLoser(List<Fleet> playersFleets, List<SalvoResult> results) {
    IntStream
        .range(0, playersFleets.size())
        .filter(p -> isFleetSunk(playersFleets.get(p)))
        .forEach(p -> results.get(p).setGameResult(GameResult.LOOSE));
  }

  private void assignWinner(List<Fleet> playersFleets, List<SalvoResult> results) {
    IntStream
        .range(0, playersFleets.size())
        .filter(p -> !isFleetSunk(playersFleets.get(p)))
        .forEach(p -> results.get(p).setGameResult(GameResult.WIN));
  }

  private boolean isFleetSunk(Fleet fleet) {
    return fleet.getShips().stream().allMatch(Ship::isSunk);
  }

}
