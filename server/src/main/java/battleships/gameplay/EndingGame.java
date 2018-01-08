package battleships.gameplay;

class EndingGame implements GameState {

  /**
   * This process method will never be run.
   */
  @Override
  public GameState process() {
    return this;
  }

  /**
   * This game state is ending the game.
   */
  @Override
  public boolean isEndOfTheGame() {
    return true;
  }
}
