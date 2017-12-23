package battleships.gameplay;

import battleships.BattleObserver;
import battleships.HandlerWrapper;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class SendingWelcomeMessageTest {
    private HandlerWrapper firstTestWrapper;
    private HandlerWrapper secondTestWrapper;
    private List<BattleObserver> handlerWrappersMocks;

    @BeforeTest
    public void setUp() {
        firstTestWrapper = mock(HandlerWrapper.class);
        secondTestWrapper = mock(HandlerWrapper.class);
        handlerWrappersMocks = Arrays.asList(firstTestWrapper, secondTestWrapper);
    }

    @Test
    public void shouldPassWhenFirstWrapperIsCorrectlyNotifiedByWelcomeMessage() {
        SendingWelcomeMessage sendingWelcomeMessage = new SendingWelcomeMessage(handlerWrappersMocks);
        sendingWelcomeMessage.process();
        verify(firstTestWrapper, atLeast(1)).sendMessage(any(WelcomeMessage.class));
    }

    @Test
    public void shouldPassWhenSecondWrapperIsCorrectlyNotifiedByWelcomeMessage() {
        SendingWelcomeMessage sendingWelcomeMessage = new SendingWelcomeMessage(handlerWrappersMocks);
        sendingWelcomeMessage.process();
        verify(secondTestWrapper, atLeast(1)).sendMessage(any(WelcomeMessage.class));
    }

    @Test
    public void shouldPassWhenGameStateIsNotEndGameState() {
        SendingWelcomeMessage sendingWelcomeMessage = new SendingWelcomeMessage(handlerWrappersMocks);
        assertThat(sendingWelcomeMessage.isEndOfTheGame()).isFalse();
    }
}