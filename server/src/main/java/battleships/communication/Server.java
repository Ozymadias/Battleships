package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a server that waits for connection from two clients.
 * It's main responsibility is to accepts client's connections and create a list of sockets
 * bound to this connections.
 */
public class Server {

  private final ServerSocket serverSocket;
  private final BattleshipLog log = BattleshipLog.provideLogger(Server.class);

  Server(ServerSocket serverSocket) {
    log.info("Server started! @ ServerPort:" + serverSocket.getLocalPort());
    this.serverSocket = serverSocket;
  }

  private Socket assignSocket(ServerSocket serverSocket) throws IOException {
    return serverSocket.accept();
  }

  /**
   * Returns int value of socket local port.
   */
  public int getServerSocketPort() {
    return serverSocket.getLocalPort();
  }

  /**
   * Waits for two players to connect to the server.
   *
   * @return list of sockets bound to ServerSockets
   * @throws IOException can throw IOException when binding to ServerSocket fails
   */
  public List<Socket> createSockets() throws IOException {
    List<Socket> sockets = new ArrayList<>();
    sockets.add(assignSocket(serverSocket));
    log.info("Player 1 connected");
    sockets.add(assignSocket(serverSocket));
    log.info("Player 2 connected");
    return sockets;
  }
}

