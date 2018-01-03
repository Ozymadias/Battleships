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

  public GameState process() {
    log.info("Sending welcome messages!");
    handlerWrappers
        .forEach(p -> p.sendMessage(new WelcomeMessage("Welcome to battleships")));
    return new WaitingForFleets(handlerWrappers);
  }

  public boolean isEndOfTheGame() {
    return false;
  }
}
