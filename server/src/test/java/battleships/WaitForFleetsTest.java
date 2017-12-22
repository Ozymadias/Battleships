package battleships;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WaitForFleetsTest {
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
    public void shouldPassWhenFirstHandlerReportsCorrectly() {
        WaitForFleets waitForFleets = new WaitForFleets(handlerWrappersMocks);
        waitForFleets.process();
        verify(firstTestWrapper, atLeast(1)).receiveMessage();
    }

    @Test
    public void shouldPassWhenSecondHandlerReportsCorrectly() {
        WaitForFleets waitForFleets = new WaitForFleets(handlerWrappersMocks);
        waitForFleets.process();
        verify(secondTestWrapper, atLeast(1)).receiveMessage();
    }

    @Test
    public void shouldPassWhenGameStateIsNotEndOfGame() {
        WaitForFleets waitForFleets = new WaitForFleets(handlerWrappersMocks);
        assertThat(waitForFleets.isEndOfTheGame()).isFalse();
    }
}