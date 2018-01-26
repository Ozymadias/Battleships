package battleships.communication.server;

import battleships.AlertWithProgressIndicator;
import battleships.communication.ClientHandler;
import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messageable;
import battleships.communication.Publisher;
import battleships.communication.messages.Salvo;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import javafx.concurrent.Task;

import java.io.IOException;

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

  /**
   * Sends message using clientHandler, and process the response from clientHandler.
   * @param event message to which answer is expected
   * @param alert instance of Alert which will be showed while waiting for response
   */
  @Override
  public void processRequest(Messageable event, AlertWithProgressIndicator alert) {
    log.info("preparing " + event.getClass() + " to send to socket");
    clientHandler.sendMessage(event);
    log.info("waiting for replay...");

    SendMessageTask sendMessageTask = new SendMessageTask();
    sendMessageTask.setOnScheduled(e -> alert.show());

    sendMessageTask.setOnSucceeded(e -> {
      alert.close();
      DataBus.getInstance().publish(sendMessageTask.getValue());
    });

    Thread th = new Thread(sendMessageTask);
    th.setDaemon(true);
    th.start();

    log.info("processing replay...");
  }

  Messageable waitForMessage() throws IOException, ClassNotFoundException {
    return clientHandler.receiveMessage();
  }

  class SendMessageTask extends Task<Messageable> {

    @Override
    protected Messageable call() throws Exception {
      return clientHandler.receiveMessage();
    }
  }
}

