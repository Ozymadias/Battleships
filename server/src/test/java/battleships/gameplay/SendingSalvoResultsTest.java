package battleships.gameplay;

import battleships.BattleObserver;
import battleships.HandlerWrapper;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SendingSalvoResultsTest {
  private List<BattleObserver> observers;
  private HandlerWrapper firstBattleObserver;
  private HandlerWrapper secondBattleObserver;
  private SalvoResult firstSalvoMock;
  private SalvoResult secondSalvoMock;
  private List<Fleet> mockFleets = mock(List.class);
  private List<SalvoResult> results;

  @BeforeMethod
  public void setUp() throws Exception {
    firstBattleObserver = mock(HandlerWrapper.class);
    secondBattleObserver = mock(HandlerWrapper.class);
    firstSalvoMock = mock(SalvoResult.class);
    secondSalvoMock = mock(SalvoResult.class);
    results = Arrays.asList(firstSalvoMock, secondSalvoMock);
    observers = Arrays.asList(firstBattleObserver, secondBattleObserver);
  }

  @Test
  public void shouldPassWhenCorrectMessagesAreSentToSecondBattleObserver() {
    SendingSalvoResults sendingSalvoResults = new SendingSalvoResults(observers, mockFleets, results, false);
    sendingSalvoResults.process();
    verify(secondBattleObserver, atLeast(1)).sendMessage(any(SalvoResult.class));
  }

  @Test
  public void shouldPassWhenCorrectMessagesAreSentToFirstBattleObserver() {
    SendingSalvoResults sendingSalvoResults = new SendingSalvoResults(observers, mockFleets, results, false);
    sendingSalvoResults.process();
    verify(firstBattleObserver, atLeast(1)).sendMessage(any(SalvoResult.class));
  }

  @Test
  public void shouldPassWhenStateIsNotEndOfTheGame() {
    SendingSalvoResults sendingSalvoResults = new SendingSalvoResults(observers, mockFleets, results, false);
    assertThat(sendingSalvoResults.isEndOfTheGame()).isFalse();
  }
}