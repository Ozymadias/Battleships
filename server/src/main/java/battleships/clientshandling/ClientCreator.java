package battleships.clientshandling;

import battleships.HandlerWrapper;
import battleships.Observers;
import battleships.communication.ClientHandlerBuilder;

import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class ClientCreator {
  /**
   * Create list of ClientHandlers.
   * @param sockets list of sockets one for each player.
   * @return list of HandlerWrappers that are used for communication later in game.
   */
  public List<Observers> createClientHandlers(List<Socket> sockets) {
    return sockets.stream()
        .map(p -> new HandlerWrapper(new ClientHandlerBuilder()
            .setSocket(p)
            .addMessageReceiver()
            .addMessageSender()
            .build())).collect(Collectors.toList());
  }
}
