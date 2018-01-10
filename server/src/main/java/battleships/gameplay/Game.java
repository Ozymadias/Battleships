package battleships.gameplay;

import battleships.Observers;
import battleships.logger.BattleshipLog;

import java.util.List;

/**
 * This class represents single battleships game between two players.
 */
public class Game {
  private final BattleshipLog log = BattleshipLog.provideLogger(Game.class);
  private final List<Observers> clientHandlers;

  public Game(List<Observers> clientHandlers) {
    this.clientHandlers = clientHandlers;
  }

  /**
   * Starts game with first initial game state SendingWelcomeMessage.
   * Then process each game state until game ends by one of outcomes.
   */
  public void start() {
    log.info("Game started");
    GameState gameState = new SendingWelcomeMessage(clientHandlers);
    do {
      gameState = gameState.process();
    } while (!gameState.isEndOfTheGame());
    log.info("Game ended");
  }
}