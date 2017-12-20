package battleships.clientshandling;

import battleships.HandlerWrapper;
import battleships.Players;
import battleships.communication.ClientHandler;
import battleships.communication.ClientHandlerBuilder;

import java.net.Socket;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static battleships.Players.*;

public class ClientCreator {

    public List<HandlerWrapper> createClientHandlers(List<Socket> sockets) {
        return sockets.stream()
                .map(p -> new HandlerWrapper(new ClientHandlerBuilder()
                        .setSocket(p)
                        .addMessageReceiver()
                        .addMessageSender()
                        .build())).collect(Collectors.toList());
    }
}
