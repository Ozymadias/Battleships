package battleships.gameplay;

import battleships.Observers;
import battleships.HandlerWrapper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WaitingForFleetsTest {

  private Observers firstBattleObserver;
  private Observers secondBattleObserver;
  private List<Observers> handlerWrappersMocks;

  @BeforeMethod
  public void setUp() {
    firstBattleObserver = mock(HandlerWrapper.class);
    secondBattleObserver = mock(HandlerWrapper.class);
    handlerWrappersMocks = Arrays.asList(firstBattleObserver, secondBattleObserver);
  }

  @Test
  public void whenProcessingWaitingForFleets_expectFirstObserverReceivesMessageOnce() throws IOException {
    //given
    WaitingForFleets waitingForFleets = new WaitingForFleets(handlerWrappersMocks);
    //when
    waitingForFleets.process();
    //then
    verify(firstBattleObserver, times(1)).receiveMessage();
  }

  @Test
  public void whenProcessingWaitingForFleets_expectSecondObserverReceivesMessageOnce() throws IOException {
    //given
    WaitingForFleets waitingForFleets = new WaitingForFleets(handlerWrappersMocks);
    //when
    waitingForFleets.process();
    //then
    verify(secondBattleObserver, times(1)).receiveMessage();
  }

  @Test
  public void whenCheckingForEndOfTheGame_expectItIsNotEndOfTheGame() {
    //given
    WaitingForFleets waitingForFleets = new WaitingForFleets(handlerWrappersMocks);
    //when
    boolean isEndOfTheGame = waitingForFleets.isEndOfTheGame();
    //then
    assertThat(isEndOfTheGame).isFalse();
  }

  @Test(expectedExceptions = RuntimeException.class,  expectedExceptionsMessageRegExp = "message not received")
  public void whenReceiveMessageMethodThrowsRuntimeException_expectProcessMethodToThrowRuntimeException() throws IOException {
    Observers playerSocketHandler = mock(Observers.class);
    List<Observers> observers = new ArrayList<>();
    observers.add(playerSocketHandler);
    observers.add(playerSocketHandler);
    doThrow(new RuntimeException("message not received")).when(playerSocketHandler).receiveMessage();
    WaitingForFleets waitingForFleets = new WaitingForFleets(observers);
    waitingForFleets.process();
  }
}