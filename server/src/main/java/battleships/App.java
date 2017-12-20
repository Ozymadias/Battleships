package battleships;

import battleships.clientshandling.ClientCreator;
import battleships.communication.ClientHandler;
import battleships.communication.Server;
import battleships.communication.ServerBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Battleship server!
 */
public class App {
    private static final int PORT = 4321;

    public static void main(String[] args) throws IOException {
        Server server = new ServerBuilder()
                .setPort(PORT)
                .openServerSocket()
                .build();

        new Game(new ClientCreator()
                .createClientHandlers(server.createSockets()))
                .start();
    }
}
