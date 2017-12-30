package battleships.communication;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerBuilder {
  private ServerSocket serverSocket;
  private Integer port;
  private final BattleshipLog log = BattleshipLog.provideLogger(ServerBuilder.class);

  public ServerBuilder setPort(Integer port) {
    this.port = port;
    return this;
  }

  /**Creates new ServerSocket for given port.
   * @return ServerBuilder with created and opened ServerSocket for port that was given as parameter.
   * @throws IOException can throw ISException when creating ServerSocket fails.
   */

  public ServerBuilder openServerSocket() throws IOException {
    serverSocket = new ServerSocket(port);
    log.info("Server started");
    return this;
  }

  public Server build() {
    return new Server(serverSocket);
  }
}