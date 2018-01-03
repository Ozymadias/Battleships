package battleships.gameplay;

import battleships.Observers;
import battleships.HandlerWrapper;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class SendingWelcomeMessageTest {

  private Observers firstBattleObserver;
  private Observers secondBattleObserver;
  private List<Observers> battleObservers;

  @BeforeMethod
  public void setUp() {
    firstBattleObserver = mock(HandlerWrapper.class);
    secondBattleObserver = mock(HandlerWrapper.class);
    battleObservers = Arrays.asList(firstBattleObserver, secondBattleObserver);
  }

  @Test
  public void whenProcessingSendingWelcomeMessage_expectWelcomeMessageIsSendToFirstObserverOnce() {
    //given
    SendingWelcomeMessage sendingWelcomeMessage = new SendingWelcomeMessage(battleObservers);
    //when
    sendingWelcomeMessage.process();
    //then
    verify(firstBattleObserver, times(1)).sendMessage(any(WelcomeMessage.class));
  }

  @Test
  public void whenProcessingSendingWelcomeMessage_expectWelcomeMessageIsSendToSecondObserverOnce() {
    //given
    SendingWelcomeMessage sendingWelcomeMessage = new SendingWelcomeMessage(battleObservers);
    //when
    sendingWelcomeMessage.process();
    //then
    verify(secondBattleObserver, times(1)).sendMessage(any(WelcomeMessage.class));
  }

  @Test
  public void whenCheckingForEndOfTheGame_expectItIsNotEndOfTheGame() {
    //given
    SendingWelcomeMessage sendingWelcomeMessage = new SendingWelcomeMessage(battleObservers);
    //when
    boolean isEndOfTheGame = sendingWelcomeMessage.isEndOfTheGame();
    //then
    assertThat(isEndOfTheGame).isFalse();
  }
}