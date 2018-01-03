package battleships.communication;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.WelcomeMessage;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Collections;
import static battleships.utils.BattleshipUtils.*;
import static org.mockito.Mockito.*;

public class ServerCommTest {

  ClientHandler clientHandler;
  ServerComm serverComm;

  @BeforeMethod
  public void beforeTest() {
    clientHandler = mock(ClientHandler.class);
    serverComm = new ServerComm(clientHandler);
  }

  @Test
  public void whenAcceptingFleetByServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Messageable messageable = new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4)));
    //when
    serverComm.accept(messageable);
    //then
    verify(clientHandler, times(1)).sendMessage(messageable);
  }

  @Test
  public void whenAcceptingSalvoByServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Messageable messageable = Salvo.createForPositions(
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99));
    //when
    serverComm.accept(messageable);
    //then
    verify(clientHandler, times(1)).sendMessage(messageable);
  }

  @Test
  public void whenAcceptingWelcomeMessageByServerComm_expectClientHandlerNotInvokeSendMessage() {
    //given
    Messageable messageable = new WelcomeMessage("hello!");
    //when
    serverComm.accept(messageable);
    //then
    verify(clientHandler, times(0)).sendMessage(messageable);
  }

  @Test
  public void whenInvokingProcessRequestOnServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Messageable messageable = Salvo.createForPositions(
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99));
    //when
    serverComm.processRequest(messageable);
    //then
    verify(clientHandler, times(1)).sendMessage(messageable);
  }

  @Test
  public void whenInvokingProcessRequestOnServerComm_expectClientHandlerInvokeReceiveMessageOnce() {
    //given
    Messageable messageable = Salvo.createForPositions(
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99));
    //when
    serverComm.processRequest(messageable);
    //then
    verify(clientHandler, times(1)).receiveMessage();
  }

  @Test
  public void whenInvokingInit_expectClientHandlerInvokeReceiveMessageOnce() {
    serverComm.init();
    verify(clientHandler, times(1)).receiveMessage();
  }
}