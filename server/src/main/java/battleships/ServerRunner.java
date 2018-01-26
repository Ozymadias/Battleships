package battleships;

import battleships.clientshandling.ClientCreator;
import battleships.communication.Server;
import battleships.communication.ServerBuilder;
import battleships.gameplay.Game;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServerRunner {

  private final BattleshipLog log = BattleshipLog.provideLogger(ServerRunner.class);
  private final ExecutorService executorService;
  private final Server server;

  private ServerRunner(ExecutorService executorService, Server server) {
    this.executorService = executorService;
    this.server = server;
  }

  static ServerRunner createInstance(int gamesAtOnceLimit, int serverPortNumber)
          throws IOException {
    Server server = ServerBuilder
        .withPort(serverPortNumber)
        .openServerSocket()
        .build();
    ExecutorService executorService = Executors.newFixedThreadPool(gamesAtOnceLimit);
    return new ServerRunner(executorService, server);
  }

  void run() {
    try {
      int count = 0;
      while (count < Integer.MAX_VALUE) {
        List<Observers> observersList
            = new ClientCreator().createClientHandlers(server.createSockets());
        Runnable task = () -> new Game(observersList).start();
        executorService.execute(task);
        count++;
      }
    } catch (IOException ex) {
      log.error(ex.getMessage());
    }
  }



}
