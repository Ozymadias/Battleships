package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.messages.WelcomeMessage;

import java.util.Map;

import static battleships.Players.*;

public class SendWelcomeMessage implements GameState {


    private final Map<Players, ClientHandler> clientHandlerMap;

    public SendWelcomeMessage(Map<Players, ClientHandler> clientHandlerMap) {
        this.clientHandlerMap = clientHandlerMap;
    }

    public GameState process() {
        clientHandlerMap.get(PLAYER1).sendMessage(new WelcomeMessage("Welcome in a new Battleships game"));
        clientHandlerMap.get(PLAYER2).sendMessage(new WelcomeMessage("Welcome in a new Battleships game"));
        return new SendGoodByeMessage(clientHandlerMap);
    }

    public boolean isEndOfTheGame() {
        return false;
    }
}
