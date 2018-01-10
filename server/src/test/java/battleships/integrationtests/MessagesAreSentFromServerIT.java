package battleships.integrationtests;

import battleships.Observers;
import battleships.clientshandling.ClientCreator;
import battleships.communication.*;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.jayway.awaitility.Awaitility.await;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class MessagesAreSentFromServerIT {
  private final static String LOCALHOST = "127.0.0.1";
  private AtomicInteger port;
  private AtomicBoolean isServerWorking;
  private AtomicBoolean isClientWorking;
  private SoftAssert softAssert = new SoftAssert();

  @BeforeMethod
  public void setUp() {
    isServerWorking = new AtomicBoolean(false);
    isClientWorking = new AtomicBoolean(false);
  }

  @Test(dataProvider = "ParametrizedSalvos")
  public void whenServerSendsSalvo_expectClientCanInterpretItCorrectly(IntegProvider integProvider) throws Exception {
    //when
    startServerAndClient(integProvider);

    Socket socket = new Socket(LOCALHOST, port.intValue());

    ClientHandler clientHandler = new ClientHandlerBuilder()
        .setSocket(socket)
        .addMessageSender()
        .addMessageReceiver()
        .build();

    Salvo expectedSalvo = (Salvo) integProvider.messageForPlayer1();

    Salvo receivedMessage = (Salvo) clientHandler.receiveMessage();
    //then
    assertThat(receivedMessage.getSalvoPositions()).isEqualTo(expectedSalvo.getSalvoPositions());

  }

  @Test(dataProvider = "ParametrizedSalvoResults")
  public void whenServerSendsSalvoResult_expectClientCanInterpretItCorrectly(IntegProvider integProvider) throws Exception {
    //when
    startServerAndClient(integProvider);

    Socket socket = new Socket(LOCALHOST, port.intValue());

    ClientHandler clientHandler = new ClientHandlerBuilder()
        .setSocket(socket)
        .addMessageSender()
        .addMessageReceiver()
        .build();

    SalvoResult expectedSalvoResult = (SalvoResult) integProvider.messageForPlayer1();

    SalvoResult receivedMessage = (SalvoResult) clientHandler.receiveMessage();
    //then
    softAssert.assertEquals(receivedMessage.getGameResult(), expectedSalvoResult.getGameResult());
    softAssert.assertEquals(receivedMessage.getResultList(), expectedSalvoResult.getResultList());
    softAssert.assertEquals(receivedMessage.getSalvoPositions(), expectedSalvoResult.getSalvoPositions());
    softAssert.assertAll();
  }

  @Test(dataProvider = "ParametrizedWelcomeMessages")
  public void whenServerSendsWelcomeMessage_expectClientCanInterpretItCorrectly(IntegProvider integProvider) throws Exception {
    //when
    startServerAndClient(integProvider);

    Socket socket = new Socket(LOCALHOST, port.intValue());

    ClientHandler clientHandler = new ClientHandlerBuilder()
        .setSocket(socket)
        .addMessageSender()
        .addMessageReceiver()
        .build();

    WelcomeMessage expectedWelcomeMessage = (WelcomeMessage) integProvider.messageForPlayer1();
    WelcomeMessage receivedMessage = (WelcomeMessage) clientHandler.receiveMessage();
    //then

    assertThat(receivedMessage).isEqualTo(expectedWelcomeMessage);
  }

  private void startClientThread() {
    new Thread(() -> {
      Socket socket = null;
      try {
        socket = new Socket(LOCALHOST, port.intValue());
        isClientWorking = new AtomicBoolean(true);
      } catch (IOException e) {
        e.printStackTrace();
      }

      ClientHandler clientHandler = new ClientHandlerBuilder()
          .setSocket(socket)
          .addMessageSender()
          .addMessageReceiver()
          .build();
      clientHandler.receiveMessage();
    }).start();
  }

  private void startServerThread(Messageable messageForPlayer0, Messageable messageForPlayer1) {
    new Thread(() -> {
      Server server;
      List<Observers> objectToReceiveMessage = new ArrayList<>();
      try {
        isServerWorking = new AtomicBoolean(true);

        server = ServerBuilder
            .withPort(0)
            .openServerSocket()
            .build();

        port = new AtomicInteger(server.getServerSocketPort());

        objectToReceiveMessage = new ClientCreator()
            .createClientHandlers(server.createSockets());

      } catch (IOException e) {
        e.printStackTrace();
      }
      objectToReceiveMessage.get(0).sendMessage(messageForPlayer0);
      objectToReceiveMessage.get(1).sendMessage(messageForPlayer1);
    }).start();
  }

  private void startServerAndClient(IntegProvider integProvider) throws InterruptedException {
    startServerThread(integProvider.messageForPlayer0(), integProvider.messageForPlayer1());
    Thread.sleep(100);
    await().atMost(2, TimeUnit.SECONDS).untilTrue(isServerWorking);
    startClientThread();
    Thread.sleep(500);
    await().atMost(2, TimeUnit.SECONDS).untilTrue(isClientWorking);
  }

  @DataProvider(name = "ParametrizedSalvos")
  public static Object[][] ParametrizedSalvos() {
    return IntegProvider.provideSalvosForIT();
  }

  @DataProvider(name = "ParametrizedSalvoResults")
  public static Object[][] ParametrizedSalvoResults() {
    return IntegProvider.provideSalvoResultsForIT();
  }

  @DataProvider(name = "ParametrizedWelcomeMessages")
  public static Object[][] ParametrizedWelcomeMessages() {
    return IntegProvider.provideWelcomeMessageForIT();
  }
}
