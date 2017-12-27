package battleships.gameplay;

public class EndingGame implements GameState{
    @Override
    public GameState process() {
        return this;
    }

    @Override
    public boolean isEndOfTheGame() {
        return true;
    }
}
