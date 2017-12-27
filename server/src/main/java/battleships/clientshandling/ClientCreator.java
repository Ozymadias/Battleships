package battleships.clientshandling;

import battleships.BattleObserver;
import battleships.HandlerWrapper;
import battleships.communication.ClientHandlerBuilder;

import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class ClientCreator {

  public List<BattleObserver> createClientHandlers(List<Socket> sockets) {
    return sockets.stream()
        .map(p -> new HandlerWrapper(new ClientHandlerBuilder()
            .setSocket(p)
            .addMessageReceiver()
            .addMessageSender()
            .build())).collect(Collectors.toList());
  }
}
