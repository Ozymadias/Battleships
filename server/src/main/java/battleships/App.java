package battleships;

import battleships.clientshandling.ClientCreator;
import battleships.communication.Server;
import battleships.communication.ServerBuilder;
import battleships.gameplay.Game;

import java.io.IOException;

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
