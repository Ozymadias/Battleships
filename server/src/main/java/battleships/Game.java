package battleships;

import battleships.logger.BattleshipLog;

import java.util.List;

public class Game {
    private final BattleshipLog log = BattleshipLog.provideLogger(Game.class);
    private List<BattleObserver> clientHandlers;

    Game(List<BattleObserver> clientHandlers) {
        this.clientHandlers = clientHandlers;
    }

    public void start() {
        log.info("Game started");
        GameState gameState = new SendWelcomeMessage(clientHandlers);
        do {
            gameState = gameState.process();
        } while (!gameState.isEndOfTheGame());
    }
}