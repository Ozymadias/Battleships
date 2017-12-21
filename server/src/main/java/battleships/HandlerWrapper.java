package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.Messagable;

public class HandlerWrapper {
    private final ClientHandler clientHandler;

    public HandlerWrapper(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void getNotified(Messagable messagable) {
        clientHandler.sendMessage(messagable);
    }

    public Messagable raport() {
        return clientHandler.receiveMessage();
    }
}
