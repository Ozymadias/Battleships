package battleships;

import battleships.game.GameResult;

public class EndOfTheGame implements GameState {
    public EndOfTheGame(GameResult draw) {
    }

    public EndOfTheGame(GameResult win, Players player) {
    }

    public GameState process() {
        return null;
    }

    public boolean isEndOfTheGame() {
        return true;
    }
}
