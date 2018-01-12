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
   * Method used to check if game ends.
   * @return boolean indicating if game ends.
   */
  boolean isEndOfTheGame();
}
