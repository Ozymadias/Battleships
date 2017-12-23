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

    public ServerBuilder openServerSocket() throws IOException {
        serverSocket = new ServerSocket(port);
        log.info("Server started");
        return this;
    }

    public Server build() {
        return new Server(serverSocket);
    }
}