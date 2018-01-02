package battleships.communication;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.game.OpponentBoardViewController;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class DataBusTest {

  DataBus dataBus;
  ServerComm serverComm;
  OpponentBoardViewController opponentBoardViewController;

  @BeforeClass
  public void beforeClass() {
    dataBus = DataBus.getInstance();
    serverComm = mock(ServerComm.class);
    opponentBoardViewController = mock(OpponentBoardViewController.class);
    dataBus.subscribeMember(opponentBoardViewController);
    dataBus.subscribePublisher(serverComm);
  }

  @Test
  public void whenPublishingMessageViaDataBus_expectOpponentBoardViewControllerMethodAcceptIsTriggeredOnce() {
    //given
    SalvoResult salvoResult = mock(SalvoResult.class);
    //when
    dataBus.publish(salvoResult);
    //then
    verify(opponentBoardViewController, times(1)).accept(salvoResult);
  }

  @Test
  public void whenSendingRequestViaDataBus_expectServerCommMethodProcessRequestIsTriggeredOnce() {
    //given
    Messagable salvo = Salvo.createForPositions(1, 2, 3, 4);
    //when
    when(serverComm.processRequest(salvo)).thenReturn(mock(SalvoResult.class));
    dataBus.sendRequest(salvo);
    //then
    verify(serverComm, times(1)).processRequest(salvo);
  }
}