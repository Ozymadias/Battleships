package battleships.clientshandling;

import battleships.Players;
import battleships.communication.ClientHandler;
import battleships.communication.ClientHandlerBuilder;

import java.net.Socket;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static battleships.Players.*;

public class ClientCreator {

    public Map<Players, ClientHandler> createClientHandlers(List<Socket> sockets) {

        Map<Players, ClientHandler> clientCreators = new EnumMap<>(Players.class);
        ClientHandler clientHandler1 = new ClientHandlerBuilder()
                .setSocket(sockets.get(0))
                .addMessageReceiver()
                .addMessageSender()
                .build();
        ClientHandler clientHandler2 = new ClientHandlerBuilder()
                .setSocket(sockets.get(1))
                .addMessageReceiver()
                .addMessageSender()
                .build();

        clientCreators.put(PLAYER1, clientHandler1);
        clientCreators.put(PLAYER2, clientHandler2);
        return clientCreators;
    }
}
