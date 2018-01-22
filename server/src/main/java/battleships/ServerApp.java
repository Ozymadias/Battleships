package battleships;

import battleships.clientshandling.ClientCreator;
import battleships.communication.Server;
import battleships.communication.ServerBuilder;
import battleships.gameplay.Game;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.net.BindException;

/**
 * Battleship server.
 */
public class ServerApp {

  /**
   * Runs server, creates socket and starts game.
   */
  public static void main(String[] args) throws IOException {
    String portString = System.getProperty("port", "4321");
    int port = Integer.parseInt(portString);
    final BattleshipLog log = BattleshipLog.provideLogger(ServerApp.class);

    try {

      Server server = ServerBuilder
              .withPort(port)
              .openServerSocket()
              .build();

      int count = 0;
      while (count < Integer.MAX_VALUE) {
        new Game(new ClientCreator()
                .createClientHandlers(server.createSockets()))
                .start();
        count++;
      }
    }catch (BindException e){
      log.info("Port number " + port + " is already used. Please rerun application with different port number.");
    }
  }
}
