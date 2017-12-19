package battleships;

import battleships.communication.ClientHandler;

import java.util.Map;

public class Game {
    private GameState gameState;
    private final Map<Players, ClientHandler> clientHandlerMap;

    public Game(Map<Players, ClientHandler> clientHandlerMap) {
        this.clientHandlerMap = clientHandlerMap;
    }

    public void start() {
        this.gameState = new SendWelcomeMessage(clientHandlerMap);
        do {
            gameState = gameState.process();
        } while (!gameState.isEndOfTheGame());
    }
}