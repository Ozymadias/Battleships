package battleships.communication;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.WelcomeMessage;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

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
    Messagable messagable = new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4)));
    //when
    serverComm.accept(messagable);
    //then
    verify(clientHandler, times(1)).sendMessage(messagable);
  }

  @Test
  public void whenAcceptingSalvoByServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Messagable messagable = Salvo.createForPositions(1, 2, 3, 4);
    //when
    serverComm.accept(messagable);
    //then
    verify(clientHandler, times(1)).sendMessage(messagable);
  }

  @Test
  public void whenAcceptingWelcomeMessageByServerComm_expectClientHandlerNotInvokeSendMessage() {
    //given
    Messagable messagable = new WelcomeMessage("hello!");
    //when
    serverComm.accept(messagable);
    //then
    verify(clientHandler, times(0)).sendMessage(messagable);
  }

  @Test
  public void whenInvokingProcessRequestOnServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Messagable messagable = Salvo.createForPositions(1, 2, 3, 4);
    //when
    serverComm.processRequest(messagable);
    //then
    verify(clientHandler, times(1)).sendMessage(messagable);
  }

  @Test
  public void whenInvokingProcessRequestOnServerComm_expectClientHandlerInvokeReceiveMessageOnce() {
    //given
    Messagable messagable = Salvo.createForPositions(1, 2, 3, 4);
    //when
    serverComm.processRequest(messagable);
    //then
    verify(clientHandler, times(1)).receiveMessage();
  }

  @Test
  public void whenInvokingInit_expectClientHandlerInvokeReceiveMessageOnce() {
    serverComm.init();
    verify(clientHandler, times(1)).receiveMessage();
  }
}