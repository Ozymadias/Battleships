package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.messages.GoodByeMessage;

import java.util.Map;

import static battleships.Players.*;

public class SendGoodByeMessage implements GameState {

    private final Map<Players, ClientHandler> clientHandlerMap;

    public SendGoodByeMessage(Map<Players, ClientHandler> clientHandlerMap) {

        this.clientHandlerMap = clientHandlerMap;
    }

    public GameState process() {
        clientHandlerMap.get(PLAYER1).sendMessage(new GoodByeMessage("See you again"));
        clientHandlerMap.get(PLAYER2).sendMessage(new GoodByeMessage("See you again"));
        return new EndOfTheGame();
    }

    public boolean isEndOfTheGame() {
        return false;
    }
}
