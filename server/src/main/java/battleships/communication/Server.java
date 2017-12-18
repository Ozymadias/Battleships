package battleships.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private final ServerSocket serverSocket;


    Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    private Socket assignSocket(ServerSocket serverSocket) throws IOException {
        return serverSocket.accept();
    }

    public List<Socket> createSockets() throws IOException {
        List<Socket> sockets = new ArrayList<>();
        sockets.add(assignSocket(serverSocket));
        sockets.add(assignSocket(serverSocket));
        return sockets;
    }
}
