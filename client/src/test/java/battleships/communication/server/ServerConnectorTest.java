package battleships.communication.server;

import battleships.communication.Messageable;
import battleships.communication.messages.WelcomeMessage;
import battleships.ships.Fleet;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerConnectorTest {

    private ServerComm serverComm;

    @BeforeTest
    private void beforeTest(){
        serverComm = mock(ServerComm.class);
    }

    @Test
    public void whenTryingToConnect_andServerSendsWelcomeMessage_expectMethodEstablishToReturnTrue(){
        //given
        Messageable welcomeMessage = mock(WelcomeMessage.class);
        when(serverComm.waitForMessage()).thenReturn(welcomeMessage);
        ServerConnector serverConnector = new ServerConnector(serverComm);
        //when - then
        assertThat(serverConnector.setUp()).isTrue();
    }

    @Test
    public void whenTryingToConnect_andServerDoesNotResponse_expectMethodEstablishToReturnFalse(){
        //given
        ServerConnector serverConnector = new ServerConnector(serverComm);
        //when - then
        assertThat(serverConnector.setUp()).isFalse();
    }

    @Test
    public void whenTryingToConnect_andServerSendsMessageOtherThenWelcomeMessage_expectMethodEstablishToReturnFalse(){
        //given
        Messageable fleet = mock(Fleet.class);
        when(serverComm.waitForMessage()).thenReturn(fleet);
        ServerConnector serverConnector = new ServerConnector(serverComm);
        //when - then
        assertThat(serverConnector.setUp()).isFalse();
    }
}