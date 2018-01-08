package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;

import java.util.List;
import java.util.stream.IntStream;

class SendingSalvoResults implements GameState {
  private final List<Observers> observers;
  private final List<Fleet> playersFleets;
  private final List<SalvoResult> results;
  private final boolean isEndOfTheGame;

  SendingSalvoResults(List<Observers> observers, List<Fleet> playersFleets, List<SalvoResult> results, boolean isEndOfTheGame) {
    this.observers = observers;
    this.playersFleets = playersFleets;
    this.results = results;
    this.isEndOfTheGame = isEndOfTheGame;
  }

  @Override
  public GameState process() {
    IntStream
        .range(0, observers.size())
        .forEach(p -> observers.get(p).sendMessage(results.get(p)));
    if (isEndOfTheGame) {
      return new EndingGame();
    } else {
      return new WaitingForSalvos(observers, playersFleets);
    }
  }

  @Override
  public boolean isEndOfTheGame() {
    return false;
  }
}
