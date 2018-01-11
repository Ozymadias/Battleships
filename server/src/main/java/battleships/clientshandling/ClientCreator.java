package battleships.clientshandling;

import battleships.HandlerWrapper;
import battleships.Observers;
import battleships.communication.ClientHandlerBuilder;

import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  The responsibility of this class is to create list of observers used to communicate
 *  to the clients. This list contains two entries, one for each client.
 */
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
            .build()))
        .collect(Collectors.toList());
  }
}
