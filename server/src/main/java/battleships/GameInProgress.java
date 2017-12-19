package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.messages.Salvo;
import battleships.ships.Fleet;

import java.util.Map;

public class GameInProgress implements GameState {
    private Map<Players, ClientHandler> clientHandlerMap;
    private Fleet fleet1;
    private Fleet fleet2;

    public GameInProgress(Map<Players, ClientHandler> clientHandlerMap, Fleet fleet1, Fleet fleet2) {

        this.clientHandlerMap = clientHandlerMap;
        this.fleet1 = fleet1;
        this.fleet2 = fleet2;
    }

    @Override
    public GameState process() {
        Salvo salvo1 = (Salvo) clientHandlerMap.get(Players.PLAYER1).receiveMessage();
        Salvo salvo2 = (Salvo) clientHandlerMap.get(Players.PLAYER2).receiveMessage();

        return this;
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
