package battleships.integrationtests;

import battleships.Observers;
import battleships.clientshandling.ClientCreator;
import battleships.communication.*;
import battleships.communication.messages.Salvo;
import battleships.ships.Fleet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReceiveMessageIT {
  private SoftAssert softAssert = new SoftAssert();

  @Test(dataProvider = "salvoProvider")
  public void whenSalvoMessageIsSendToServer_expectSameMessageToBeReceivedByServer(IntegProvider integProvider) throws Exception {
    //given
    final String LOCALHOST = "127.0.0.1";
    Server server = createServer();
    //when
    runSenderThread(LOCALHOST, server.getServerSocketPort(), integProvider.messageForPlayer0());
    Thread.sleep(1000);
    runSenderThread(LOCALHOST, server.getServerSocketPort(), integProvider.messageForPlayer1());
    List<Observers> observers = new ClientCreator().createClientHandlers(server.createSockets());

    Salvo player0Message = (Salvo) observers.get(0).receiveMessage();
    Salvo player1Message = (Salvo) observers.get(1).receiveMessage();
    Salvo expected0Salvo = (Salvo) integProvider.messageForPlayer0();
    Salvo expected1Salvo = (Salvo) integProvider.messageForPlayer1();

    softAssert.assertEquals(expected0Salvo.getSalvoPositions(), player0Message.getSalvoPositions());
    softAssert.assertEquals(expected1Salvo.getSalvoPositions(), player1Message.getSalvoPositions());
    softAssert.assertAll();
  }

  @Test(dataProvider = "fleetProvider")
  public void whenFleetMessageIsSendToServer_expectSameMessageToBeReceivedByServer(IntegProvider integProvider) throws Exception {
    //given
    final String LOCALHOST = "127.0.0.1";
    Server server = createServer();
    //when
    runSenderThread(LOCALHOST, server.getServerSocketPort(), integProvider.messageForPlayer0());
    Thread.sleep(1000);
    runSenderThread(LOCALHOST, server.getServerSocketPort(), integProvider.messageForPlayer1());
    List<Observers> observers = new ClientCreator().createClientHandlers(server.createSockets());

    Fleet player0Message = (Fleet) observers.get(0).receiveMessage();
    Fleet player1Message = (Fleet) observers.get(1).receiveMessage();
    Fleet expected0Fleet = (Fleet) integProvider.messageForPlayer0();
    Fleet expected1Fleet = (Fleet) integProvider.messageForPlayer1();

    softAssert.assertEquals(expected0Fleet.getAllPositions(), player0Message.getAllPositions());
    softAssert.assertEquals(expected1Fleet.getAllPositions(), player1Message.getAllPositions());
    softAssert.assertAll();
  }

  private Server createServer() throws IOException {
    return ServerBuilder.withPort(0)
        .openServerSocket()
        .build();
  }

  private void runSenderThread(String IP, int port, Messageable messageable) {
    new Thread(() -> {
      Socket socket = null;
      try {
        socket = new Socket(IP, port);
      } catch (IOException e) {
        e.printStackTrace();
      }

      ClientHandler clientHandler = new ClientHandlerBuilder()
          .setSocket(socket)
          .addMessageSender()
          .addMessageReceiver()
          .build();

      clientHandler.sendMessage(messageable);
    }).start();
  }

  @DataProvider(name = "salvoProvider")
  public static Object[][] salvoProvider() {
    return IntegProvider.provideSalvosForIT();
  }

  @DataProvider(name = "fleetProvider")
  public static Object[][] fleetProvider() {
    return IntegProvider.provideFleetsForIT();
  }
}
