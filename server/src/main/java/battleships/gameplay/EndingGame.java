package battleships.gameplay;

import battleships.logger.BattleshipLog;

public class EndingGame implements GameState{
    private final BattleshipLog log = BattleshipLog.provideLogger(EndingGame.class);
    @Override
    public GameState process() {
        return this;
    }

    @Override
    public boolean isEndOfTheGame() {
        return true;
    }
}
