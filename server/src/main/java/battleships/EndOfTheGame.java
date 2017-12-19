package battleships;

public class EndOfTheGame implements GameState {
    public GameState process() {
        return this;
    }

    public boolean isEndOfTheGame() {
        return true;
    }
}
