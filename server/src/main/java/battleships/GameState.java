package battleships;

public interface GameState {
    GameState process();

    boolean isEndOfTheGame();
}
