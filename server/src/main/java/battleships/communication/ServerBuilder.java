package battleships.communication;

import java.io.IOException;
import java.net.ServerSocket;

public interface ServerBuilder {

  /**
   * Creates instance of SocketOpener.
   *
   * @param port accept int value of port as parameter
   * @return new instance of SocketOpener with given port
   */
  static SocketOpener withPort(int port) {
    return new SocketOpener(port);
  }

  class SocketOpener {
    private final int port;

    SocketOpener(int port) {
      this.port = port;
    }

    /**
     * Creates new instance of ServerCreator with ServerSocket created from port int value.
     *
     * @return new instance of ServerCreator
     * @throws IOException may throw IOException when connection fails.
     */
    public ServerCreator openServerSocket() throws IOException {
      return new ServerCreator(new ServerSocket(port));
    }
  }

  class ServerCreator {
    private ServerSocket serverSocket;

    ServerCreator(ServerSocket serverSocket) {
      this.serverSocket = serverSocket;
    }

    /**
     * Creates server.
     *
     * @return new instance of Server with opened ServerSocket.
     */
    public Server build() {
      return new Server(serverSocket);
    }
  }
}

