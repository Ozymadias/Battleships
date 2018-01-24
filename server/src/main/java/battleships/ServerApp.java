package battleships;

import battleships.clientshandling.ClientCreator;
import battleships.communication.Server;
import battleships.communication.ServerBuilder;
import battleships.gameplay.Game;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.net.BindException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Battleship server.
 */
public class ServerApp {

  /**
   * Runs server, creates socket and starts game.
   * @param args passed arguments are not used
   */
  public static void main(String[] args) {
    String portString = System.getProperty("port", "4321");
    int port = Integer.parseInt(portString);
    final BattleshipLog log = BattleshipLog.provideLogger(ServerApp.class);
    ExecutorService executor = Executors.newFixedThreadPool(5);

    try {

      Server server = ServerBuilder
              .withPort(port)
              .openServerSocket()
              .build();

      int count = 0;
      while (count < Integer.MAX_VALUE) {
        List<Observers> observersList
            = new ClientCreator().createClientHandlers(server.createSockets());

        Runnable task = () -> {
          new Game(observersList).start();
        };

        executor.execute(task);

        count++;
      }
    } catch (BindException e) {
      log.info("Port number " + port + " is already used."
          + " Please rerun application with different port number.");
    } catch (IOException e) {
      log.error(e);
    }
  }
}
