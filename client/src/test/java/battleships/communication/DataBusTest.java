package battleships.communication;

import battleships.communication.databus.DataBus;
import battleships.communication.databus.Publisher;
import battleships.communication.databus.data.SalvoAdapter;
import battleships.communication.databus.data.SalvoResultAdapter;
import battleships.communication.messages.Salvo;
import battleships.game.OpponentBoardViewController;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static battleships.utils.BattleshipUtils.*;
import static org.mockito.Mockito.*;

public class DataBusTest {

  DataBus dataBus;
  Publisher serverComm;
  OpponentBoardViewController opponentBoardViewController;

  @BeforeClass
  public void beforeClass() {
    dataBus = DataBus.getInstance();
    serverComm = mock(Publisher.class);
    opponentBoardViewController = mock(OpponentBoardViewController.class);
    dataBus.subscribeMember(opponentBoardViewController);
    dataBus.subscribePublisher(serverComm);
  }

  @Test
  public void whenPublishingMessageViaDataBus_expectOpponentBoardViewControllerMethodAcceptIsTriggeredOnce() {
    //given
    SalvoResultAdapter salvoResultAdapter = mock(SalvoResultAdapter.class);
    //when
    dataBus.publish(salvoResultAdapter);
    //then
    verify(opponentBoardViewController, times(1)).visit(salvoResultAdapter);
  }


  @Test
  public void whenSendingRequestViaDataBus_expectServerCommMethodProcessRequestIsTriggeredOnce() {
    //given
    Salvo salvo = Salvo.createForPositions(
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99),
        provideRandomNumber(0,99));
    SalvoAdapter salvoAdapter = new SalvoAdapter();
    salvoAdapter.setSalvo(salvo);
    //when
    when(serverComm.processSalvoRequest(salvo)).thenReturn(mock(SalvoAdapter.class));
    dataBus.publishRequest(salvo);
    //then
    verify(serverComm, times(1)).processSalvoRequest(salvo);
  }
}