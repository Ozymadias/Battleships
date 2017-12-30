package battleships.gameplay;

import battleships.Observers;
import battleships.HandlerWrapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WaitingForFleetsTest {
  private HandlerWrapper firstTestWrapper;
  private HandlerWrapper secondTestWrapper;
  private List<Observers> handlerWrappersMocks;

  @BeforeTest
  public void setUp() {
    firstTestWrapper = mock(HandlerWrapper.class);
    secondTestWrapper = mock(HandlerWrapper.class);
    handlerWrappersMocks = Arrays.asList(firstTestWrapper, secondTestWrapper);
  }

  @Test
  public void shouldPassWhenFirstHandlerReportsCorrectly() {
    WaitingForFleets waitingForFleets = new WaitingForFleets(handlerWrappersMocks);
    waitingForFleets.process();
    verify(firstTestWrapper, atLeast(1)).receiveMessage();
  }

  @Test
  public void shouldPassWhenSecondHandlerReportsCorrectly() {
    WaitingForFleets waitingForFleets = new WaitingForFleets(handlerWrappersMocks);
    waitingForFleets.process();
    verify(secondTestWrapper, atLeast(1)).receiveMessage();
  }

  @Test
  public void shouldPassWhenGameStateIsNotEndOfGame() {
    WaitingForFleets waitingForFleets = new WaitingForFleets(handlerWrappersMocks);
    assertThat(waitingForFleets.isEndOfTheGame()).isFalse();
  }
}