package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.Salvo;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import com.sun.nio.sctp.IllegalReceiveException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class WaitingForSalvos implements GameState {

  private final List<Observers> observers;
  private final List<Fleet> playersFleets;
  private final BattleshipLog log = BattleshipLog.provideLogger(WaitingForSalvos.class);

  WaitingForSalvos(List<Observers> observers, List<Fleet> playersFleets) {
    this.observers = observers;
    this.playersFleets = playersFleets;
  }

  /**
   * This method process current game state. We receive salvos from both clients.
   *
   * @return next game state that is SalvoProcessing.
   */
  @Override
  public GameState process() throws IOException {
    try {
      log.info("Waiting for salvos");
      List<Salvo> salvos = observers.stream().map(p -> (Salvo) p.receiveMessage())
              .collect(Collectors.toList());
      return new SalvosProcessing(observers, playersFleets, salvos);
    } catch (IllegalReceiveException ex) {
      throw new IOException(ex.getMessage());
    }
  }

  /**
   * This game state is not ending the game.
   */
  @Override
  public boolean isEndOfTheGame() {
    return false;
  }
}
