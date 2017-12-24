package battleships.communication;

import battleships.communication.messages.Salvo;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.io.IOException;
import java.net.Socket;

public class ServerComm implements Member, Publisher {

    private final ClientHandler clientHandler;
    private final BattleshipLog log = BattleshipLog.provideLogger(ServerComm.class);

    private ServerComm(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public static ServerComm build(String host, Integer port) throws IOException {
        Socket socket = new Socket(host, port);
        ClientHandler clientHandler = new ClientHandlerBuilder().setSocket(socket).addMessageSender().addMessageReceiver().build();
        return new ServerComm(clientHandler);
    }

    @Override
    public void accept(Messagable event) {
        if(event instanceof Fleet || event instanceof Salvo) {
            log.info("preparing " + event.getClass() + " to send to socket");
            clientHandler.sendMessage(event);
        }
    }

    @Override
    public Messagable processRequest(Messagable event) {
        log.info("preparing " + event.getClass() + " to send to socket");
        clientHandler.sendMessage(event);
        log.info("wating for replay...");
        Messagable messagable = clientHandler.receiveMessage();
        log.info("processing replay...");
        return messagable;
    }

    public void init() {
        //waiting for welcome message
        clientHandler.receiveMessage();
    }

}
