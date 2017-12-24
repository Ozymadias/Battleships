package battleships.communication;

import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

import java.io.IOException;
import java.net.Socket;

public class ServerComm implements Member{

    private ClientHandler clientHandler;
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
    //albo może accept ma też zwracać wiadmość??
    public void accept(Messagable event) {
        log.info("preparing " + event.getClass() + " to send via data bus" + event.toString());
        clientHandler.sendMessage(event);

        //z tym że po wysłaniu salwy każdorazowo powinien nasłuchiwać odpowiedzi -> a może ta logika w databus??
    }

    //nasłuchiwanie przychodzącej wiadomości na osobnym wątku??
}
