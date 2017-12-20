package battleships;

import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class SendWelcomeMessageTest {
    private HandlerWrapper firstTestWrapper;
    private HandlerWrapper secondTestWrapper;
    private List<HandlerWrapper> handlerWrappersMocks;

    @BeforeTest
    public void setUp() {
        firstTestWrapper = mock(HandlerWrapper.class);
        secondTestWrapper = mock(HandlerWrapper.class);
        handlerWrappersMocks = Arrays.asList(firstTestWrapper, secondTestWrapper);
    }

    @Test
    public void shouldPassWhenFirstWrapperIsCorrectlyNotifiedByWelcomeMessage() {
        SendWelcomeMessage sendWelcomeMessage = new SendWelcomeMessage(handlerWrappersMocks);
        sendWelcomeMessage.process();
        verify(firstTestWrapper, atLeast(1)).getNotified(any(WelcomeMessage.class));
    }

    @Test
    public void shouldPassWhenSecondWrapperIsCorrectlyNotifiedByWelcomeMessage() {
        SendWelcomeMessage sendWelcomeMessage = new SendWelcomeMessage(handlerWrappersMocks);
        sendWelcomeMessage.process();
        verify(secondTestWrapper, atLeast(1)).getNotified(any(WelcomeMessage.class));
    }

    @Test
    public void shouldPassWhenGameStateIsNotEndGameState() {
        SendWelcomeMessage sendWelcomeMessage = new SendWelcomeMessage(handlerWrappersMocks);
        assertThat(sendWelcomeMessage.isEndOfTheGame()).isFalse();
    }
}