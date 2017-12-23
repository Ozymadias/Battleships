package battleships.gameplay;

import battleships.BattleObserver;
import battleships.HandlerWrapper;
import battleships.communication.messages.Salvo;
import battleships.ships.Fleet;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class WaitingForSalvosTest {
    private HandlerWrapper firstBattleObserver;
    private HandlerWrapper secondBattleObserver;
    private List<BattleObserver> observersList;
    private List<Fleet> fleets;

    @BeforeTest
    public void setUp() {
        firstBattleObserver = mock(HandlerWrapper.class);
        secondBattleObserver = mock(HandlerWrapper.class);
        observersList = Arrays.asList(firstBattleObserver, secondBattleObserver);
        fleets = mock(ArrayList.class);
    }

    @Test
    public void shouldPassWhenFirstBattleObserverReceivesMessage() {
        WaitingForSalvos waitingForSalvosTest = new WaitingForSalvos(observersList, fleets);
        waitingForSalvosTest.process();
        verify(firstBattleObserver, atLeast(1)).receiveMessage();
    }

    @Test
    public void shouldPassWhenSecondBattleObserverReceivesMessage() {
        WaitingForSalvos waitingForSalvosTest = new WaitingForSalvos(observersList, fleets);
        waitingForSalvosTest.process();
        verify(secondBattleObserver, atLeast(1)).receiveMessage();
    }

    @Test
    public void shouldPassWhenWaitingForSalvosIsNotEndOfGame() {
        WaitingForSalvos waitingForSalvosTest = new WaitingForSalvos(observersList, fleets);
        assertThat(waitingForSalvosTest.isEndOfTheGame()).isFalse();
    }
}