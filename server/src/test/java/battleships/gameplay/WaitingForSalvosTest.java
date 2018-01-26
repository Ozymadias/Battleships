package battleships.gameplay;

import battleships.HandlerWrapper;
import battleships.Observers;
import battleships.ships.Fleet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WaitingForSalvosTest {
  private HandlerWrapper firstBattleObserver;
  private HandlerWrapper secondBattleObserver;
  private List<Observers> observersList;
  private List<Fleet> fleets;

  @BeforeMethod
  public void setUp() {
    firstBattleObserver = mock(HandlerWrapper.class);
    secondBattleObserver = mock(HandlerWrapper.class);
    observersList = Arrays.asList(firstBattleObserver, secondBattleObserver);
    fleets = mock(ArrayList.class);
  }

  @Test
  public void whenProcessingWaitingForSalvos_expectFirstObserverReceivesMessageOnce() throws IOException {
    //given
    WaitingForSalvos waitingForSalvosTest = new WaitingForSalvos(observersList, fleets);
    //when
    waitingForSalvosTest.process();
    //then
    verify(firstBattleObserver, times(1)).receiveMessage();
  }

  @Test
  public void whenProcessingWaitingForSalvos_expectSecondObserverReceivesMessageOnce() throws IOException {
    //given
    WaitingForSalvos waitingForSalvosTest = new WaitingForSalvos(observersList, fleets);
    //when
    waitingForSalvosTest.process();
    //then
    verify(secondBattleObserver, times(1)).receiveMessage();
  }

  @Test
  public void whenCheckingForEndOfTheGame_expectItIsNotEndOfTheGame() {
    //given
    WaitingForSalvos waitingForSalvosTest = new WaitingForSalvos(observersList, fleets);
    //when
    boolean isEndOfTheGame = waitingForSalvosTest.isEndOfTheGame();
    //then
    assertThat(isEndOfTheGame).isFalse();
  }
}