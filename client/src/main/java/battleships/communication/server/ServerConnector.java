package battleships.communication.server;

import battleships.communication.ClientHandler;
import battleships.communication.ClientHandlerBuilder;
import battleships.communication.messages.WelcomeMessage;
import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.net.Socket;

/**
 * Class responsible for establishing connection with server.
 */
public class ServerConnector {

  private final ServerComm serverComm;
  private final BattleshipLog log = BattleshipLog.provideLogger(ServerConnector.class);

  ServerConnector(ServerComm serverComm) {
    this.serverComm = serverComm;
  }

  /**
   * Creates instance of ServerConnector by given host and port.
   *
   * @param host server host
   * @param port server port to connect
   * @return instance of ServerConnector
   * @throws IOException if connection failed
   */
  public static ServerConnector buildWithHostAndPort(String host, Integer port)
      throws IOException {
    return buildWithSocket(new Socket(host, port));
  }

  /**
   * Creates instance of ServerConnector by given socket.
   *
   * @param socket socket responsible for connection to server
   * @return instance of ServerConnector
   */
  static ServerConnector buildWithSocket(Socket socket) {
    ClientHandler clientHandler = new ClientHandlerBuilder()
        .setSocket(socket)
        .addMessageSender()
        .addMessageReceiver()
        .build();
    ServerComm serverComm = new ServerComm(clientHandler);
    return new ServerConnector(serverComm);
  }

  /**
   * Checks if connection to server is properly set up by waiting for proper message from server.
   *
   * @return true if connection is valid, false otherwise
   */
  public boolean setUp() {
    boolean connectionEstablish = false;
    try {
      if (serverComm.waitForMessage() instanceof WelcomeMessage) {
        connectionEstablish = true;
        serverComm.register();
      }
    } catch (IOException | ClassNotFoundException ex) {
      log.error(ex.getMessage());
    }
    return connectionEstablish;
  }

}
