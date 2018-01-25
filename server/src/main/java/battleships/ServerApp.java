package battleships;

import battleships.logger.BattleshipLog;

import java.io.IOException;
import java.net.BindException;

/**
 * Battleship server.
 */
public class ServerApp {

  private static final int GAMES_AT_ONCE_LIMIT = 5;

  /**
   * Runs server, creates socket and starts game.
   * @param args passed arguments are not used
   */
  public static void main(String[] args) {
    BattleshipLog log = BattleshipLog.provideLogger(ServerApp.class);
    String portString = System.getProperty("port", "4321");
    if (new PortValidator().validate(portString)) {
      try {
        ServerRunner.asInstance(GAMES_AT_ONCE_LIMIT, Integer.parseInt(portString)).run();
      } catch (BindException ex) {
        log.info("Port number " + portString + " is already used."
            + " Please rerun application with different port number.");
      } catch (IOException ex) {
        log.error(ex);
      }
    } else {
      log.info(portString + " is not valid port number");
    }
  }



}
