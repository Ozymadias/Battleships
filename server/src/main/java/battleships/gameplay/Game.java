package battleships.gameplay;

import battleships.BattleObserver;
import battleships.logger.BattleshipLog;

import java.util.List;

public class Game {
  private final BattleshipLog log = BattleshipLog.provideLogger(Game.class);
  private final List<BattleObserver> clientHandlers;

  public Game(List<BattleObserver> clientHandlers) {
    this.clientHandlers = clientHandlers;
  }

  public void start() {
    log.info("Game started");
    GameState gameState = new SendingWelcomeMessage(clientHandlers);
    do {
      gameState = gameState.process();
    } while (!gameState.isEndOfTheGame());
    log.info("Game ended");
  }
}