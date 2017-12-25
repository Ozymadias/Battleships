package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HandlerWrapperTest {
    private ClientHandler clientHandlerMock;
    @BeforeTest
    public void setUp() {
        clientHandlerMock = mock(ClientHandler.class);
    }

    @Test
    public void shouldPassWhenMethodReceiveMessageIsCorrectlyCalledForHandlerMock() {
        //given
        HandlerWrapper handlerWrapper = new HandlerWrapper(clientHandlerMock);
        //when
        handlerWrapper.receiveMessage();
        //then
        verify(clientHandlerMock, atLeast(1)).receiveMessage();
    }

    @Test
    public void shouldPassWhenMethodSendMessageIsCorrectlyCalledForHandlerMockWithGivenMessageable() {
        //given
        HandlerWrapper handlerWrapper = new HandlerWrapper(clientHandlerMock);
        //when
        handlerWrapper.sendMessage(new WelcomeMessage());
        //then
        verify(clientHandlerMock, atLeast(1)).sendMessage(any(WelcomeMessage.class));
    }
}