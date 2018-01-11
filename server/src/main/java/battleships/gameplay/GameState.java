package battleships.gameplay;

/**
 * This interface represents game state that can be processed and also indicates
 * the end of the game.
 */
public interface GameState {
  /**
   * This method process current game state and return next game state.
   */
  GameState process();

  /**
   * This method returns if current game state indicates end of the game.
   */
  boolean isEndOfTheGame();
}
