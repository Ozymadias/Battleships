package battleships.communication.server;

import battleships.communication.ClientHandler;
import battleships.communication.Messageable;
import battleships.communication.databus.data.FleetAdapter;
import battleships.communication.databus.data.SalvoAdapter;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.WelcomeMessage;
import battleships.communication.server.ServerComm;
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
    FleetAdapter fleetAdapter = new FleetAdapter();
    fleetAdapter.setFleet(new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4))));
    //when
    serverComm.visit(fleetAdapter);
    //then
    verify(clientHandler).sendMessage(fleetAdapter.getFleet());
  }

  @Test
  public void whenAcceptingSalvoByServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Salvo salvo= Salvo.createForPositions(
            provideRandomNumber(0,99),
            provideRandomNumber(0,99),
            provideRandomNumber(0,99),
            provideRandomNumber(0,99));
    SalvoAdapter salvoAdapter = new SalvoAdapter();
    salvoAdapter.setSalvo(salvo);
    //when
    serverComm.visit(salvoAdapter);
    //then
    verify(clientHandler).sendMessage(salvo);
  }

  @Test
  public void whenInvokingProcessRequestOnServerComm_expectClientHandlerInvokeSendMessageOnce() {
    //given
    Salvo salvo = Salvo.createForPositions(
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99));
    //when
    serverComm.processSalvoRequest(salvo);
    //then
    verify(clientHandler).sendMessage(salvo);
  }

  @Test
  public void whenInvokingProcessRequestOnServerComm_expectClientHandlerInvokeReceiveMessageOnce() {
    //given
    Salvo salvo = Salvo.createForPositions(
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99));
    //when
    serverComm.processSalvoRequest(salvo);
    //then
    verify(clientHandler, times(1)).receiveMessage();
  }

  @Test
  public void whenInvokingWaitForMessage_expectClientHandlerInvokeReceiveMessageOnce() {
    serverComm.waitForMessage();
    verify(clientHandler, times(1)).receiveMessage();
  }
}