package battleships.communication;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerBuilder {
    private ServerSocket serverSocket;
    private Integer port;

    public ServerBuilder setPort(Integer port) {
        this.port = port;
        return this;
    }

    public ServerBuilder openServerSocket() throws IOException {
        serverSocket = new ServerSocket(port);
        return this;
    }

    public Server build() {
        return new Server(serverSocket);
    }
}