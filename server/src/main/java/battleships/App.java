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

    public static void main(String[] args) throws IOException {
        String portString = System.getProperty("port","4321");
        int port = Integer.valueOf(portString);

        Server server = new ServerBuilder()
                .setPort(port)
                .openServerSocket()
                .build();

        while (true) {
            new Game(new ClientCreator()
                    .createClientHandlers(server.createSockets()))
                    .start();
        }
    }
}
