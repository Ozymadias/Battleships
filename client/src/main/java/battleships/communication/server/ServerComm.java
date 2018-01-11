package battleships.communication.server;

import battleships.communication.ClientHandler;
import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messageable;
import battleships.communication.Publisher;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;

class ServerComm implements Member, Publisher {

  private final BattleshipLog log = BattleshipLog.provideLogger(ServerComm.class);

  private final ClientHandler clientHandler;

  ServerComm(ClientHandler clientHandler) {
    this.clientHandler = clientHandler;
  }

  void register() {
    DataBus.getInstance().subscribeMember(this);
    DataBus.getInstance().subscribePublisher(this);
  }

  @Override
  public void accept(Messageable data) {
    if (data instanceof Fleet || data instanceof Salvo) {
      log.info("preparing " + data.getClass() + " to send to socket");
      clientHandler.sendMessage(data);
    }
  }

  @Override
  public Messageable processRequest(Messageable event) {
    log.info("preparing " + event.getClass() + " to send to socket");
    clientHandler.sendMessage(event);
    log.info("wating for replay...");
    Messageable messageable = clientHandler.receiveMessage();
    log.info("processing replay...");
    return messageable;
  }


  Messageable waitForMessage() {
    return clientHandler.receiveMessage();
  }
}
