package battleships.communication;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

public class DataBusTest {

    DataBus dataBus;
    ServerComm serverComm;

    @BeforeClass
    public void beforeClass(){
        dataBus = DataBus.getInstance();
        serverComm = mock(ServerComm.class);
        dataBus.subscribeMember(serverComm);
        dataBus.subscribePublisher(serverComm);
    }

    @Test
    public void givenDataBusWithServerCommAsMember_whenPublishingMessageViaDataBus_thenServerCommMethodAcceptShouldBeTriggerred(){
        //given
        Messagable welcomeMessage = new WelcomeMessage("hello!");
        //when
        dataBus.publish(welcomeMessage);
        //then
        verify(serverComm, times(1)).accept(welcomeMessage);
    }

    @Test
    public void givenDataBusWithServerCommAsMember_whenSendingRequestViaDataBus_thenServerCommMethodProcessRequestShouldBeTriggerred() {
        //given
        Messagable salvo = Salvo.createForPositions(1, 2, 3, 4);
        //when
        when(serverComm.processRequest(salvo)).thenReturn(mock(SalvoResult.class));
        dataBus.sendRequest(salvo);
        //then
        verify(serverComm, times(1)).processRequest(salvo);
    }
}