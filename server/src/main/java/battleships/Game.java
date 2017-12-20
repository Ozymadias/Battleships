package battleships;

import java.util.List;

public class Game {

    private List<HandlerWrapper> clientHandlers;

    Game(List<HandlerWrapper> clientHandlers) {
        this.clientHandlers = clientHandlers;
    }

    public void start() {
        GameState gameState = new SendWelcomeMessage(clientHandlers);
        do {
            gameState = gameState.process();
        } while (!gameState.isEndOfTheGame());
    }
}