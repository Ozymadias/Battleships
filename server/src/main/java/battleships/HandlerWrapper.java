package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.Messagable;

public class HandlerWrapper implements BattleObserver {
    private final ClientHandler clientHandler;

    public HandlerWrapper(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @Override
    public void getNotified(Messagable messagable) {
        clientHandler.sendMessage(messagable);
    }

    @Override
    public Messagable raport() {
        return clientHandler.receiveMessage();
    }

}
