package battleships;

public class EndOfTheGame implements GameState {
    public GameState process() {
        return null;
    }

    public boolean isEndOfTheGame() {
        return true;
    }
}
