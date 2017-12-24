package battleships.gameplay;

public interface GameState {
    GameState process();

    boolean isEndOfTheGame();
}
