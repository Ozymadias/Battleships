package battleships;

import battleships.communication.ClientHandler;
import battleships.ships.Fleet;

import java.util.Map;

public class WaitForFleets implements GameState {

    private final Map<Players, ClientHandler> clientHandlerMap;

    public WaitForFleets(Map<Players, ClientHandler> clientHandlerMap) {
        this.clientHandlerMap = clientHandlerMap;
    }
    @Override
    public GameState process() {
        Fleet fleet1 = (Fleet) clientHandlerMap.get(Players.PLAYER1).receiveMessage();
        Fleet fleet2 = (Fleet) clientHandlerMap.get(Players.PLAYER2).receiveMessage();
        clientHandlerMap.get(Players.PLAYER2).receiveMessage();

        return new GameInProgress(clientHandlerMap, fleet1, fleet2 );
    }

    @Override
    public boolean isEndOfTheGame() {
        return false;
    }
}
