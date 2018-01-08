package battleships.communication.server;

import battleships.communication.ClientHandler;
import battleships.communication.databus.DataBus;
import battleships.communication.databus.DataType;
import battleships.communication.databus.DataTypeVisitor;
import battleships.communication.Messageable;
import battleships.communication.databus.Publisher;
import battleships.communication.databus.data.FleetAdapter;
import battleships.communication.databus.data.SalvoAdapter;
import battleships.communication.databus.data.SalvoCountAdapter;
import battleships.communication.databus.data.SalvoResultAdapter;
import battleships.logger.BattleshipLog;

class ServerComm implements DataTypeVisitor, Publisher {

  private final BattleshipLog log = BattleshipLog.provideLogger(ServerComm.class);

  private final ClientHandler clientHandler;

  ServerComm(ClientHandler clientHandler) {
    this.clientHandler = clientHandler;
  }

  void register() {
    DataBus.getInstance().subscribeMember(this);
    DataBus.getInstance().subscribePublisher(this);
  }

  Messageable waitForMessage() {
    return clientHandler.receiveMessage();
  }

  @Override
  public void visit(SalvoAdapter salvoAdapter) {
    log.info("preparing salvo to send to socket");
    clientHandler.sendMessage(salvoAdapter.getSalvo());
  }

  @Override
  public void visit(SalvoCountAdapter salvoCountAdapter) {
    //do nothing
  }

  @Override
  public void visit(SalvoResultAdapter salvoResultAdapter) {
    //do nothing
  }

  @Override
  public void visit(FleetAdapter fleetAdapter) {
    log.info("preparing fleet to send to socket");
    clientHandler.sendMessage(fleetAdapter.getFleet());
  }

  @Override
  public Messageable processRequest(DataType event) {
    return null;
  }
}
