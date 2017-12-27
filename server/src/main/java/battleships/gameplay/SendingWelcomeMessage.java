package battleships.gameplay;

import battleships.BattleObserver;
import battleships.communication.messages.WelcomeMessage;
import battleships.logger.BattleshipLog;

import java.util.List;

class SendingWelcomeMessage implements GameState {
  private final BattleshipLog log = BattleshipLog.provideLogger(SendingWelcomeMessage.class);

  private final List<BattleObserver> handlerWrappers;

  SendingWelcomeMessage(List<BattleObserver> clientHandlerMap) {
    this.handlerWrappers = clientHandlerMap;
  }

  public GameState process() {
    log.info("Sending welcome messages!");
    handlerWrappers
        .forEach(p -> p.sendMessage(new WelcomeMessage("Im death destroyer of worlds and you enter my domain")));
    return new WaitingForFleets(handlerWrappers);
  }

  public boolean isEndOfTheGame() {
    return false;
  }
}
