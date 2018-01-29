package battleships.gameplay;

import battleships.Observers;
import battleships.communication.messages.WelcomeMessage;
import battleships.logger.BattleshipLog;

import java.util.List;

class SendingWelcomeMessage implements GameState {
  private final BattleshipLog log = BattleshipLog.provideLogger(SendingWelcomeMessage.class);

  private final List<Observers> handlerWrappers;

  SendingWelcomeMessage(List<Observers> clientHandlerMap) {
    this.handlerWrappers = clientHandlerMap;
  }

  /**
   * This method process current game state. We send WelcomeMessage to both clients.
   *
   * @return next game state that is WaitingForFleets.
   */
  public GameState process() {
    log.info("Sending welcome messages!");
    handlerWrappers
        .forEach(p -> p.sendMessage(new WelcomeMessage("Welcome to battleships")));
    return new WaitingForFleets(handlerWrappers);
  }

}
