package battleships.gameplay;

import battleships.Observers;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import com.sun.nio.sctp.IllegalReceiveException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

class WaitingForFleets implements GameState {
  private final BattleshipLog log = BattleshipLog.provideLogger(WaitingForFleets.class);
  private final List<Observers> observers;

  WaitingForFleets(List<Observers> observers) {
    this.observers = observers;
  }

  /**
   * This method process current game state. We receive fleets from both clients.
   *
   * @return next game state that is WaitingForSalvos.
   */
  @Override
  public GameState process() throws IOException {
    try {
      log.info("Waiting for fleet");
      return new WaitingForSalvos(observers, observers
              .stream()
              .map(handlerWrapper -> (Fleet) handlerWrapper.receiveMessage())
              .collect(Collectors.toList()));
    } catch (IllegalReceiveException ex) {
      throw new IOException(ex.getMessage());
    }
  }

}
