package battleships;

import battleships.communication.messages.WelcomeMessage;

import java.util.List;

public class SendWelcomeMessage implements GameState {


    private final List<HandlerWrapper> handlerWrappers;

    SendWelcomeMessage(List<HandlerWrapper> clientHandlerMap) {
        this.handlerWrappers = clientHandlerMap;
    }

    public GameState process() {
        handlerWrappers
                .forEach(p -> p.getNotified(new WelcomeMessage("Im death destroyer of worlds and you enter my domain")));
        return new WaitForFleets(handlerWrappers);
    }

    public boolean isEndOfTheGame() {
        return false;
    }
}
