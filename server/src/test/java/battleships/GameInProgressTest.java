package battleships;

import battleships.communication.Messagable;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class GameInProgressTest {
    private HandlerWrapper firstTestWrapper;
    private HandlerWrapper secondTestWrapper;
    private HandlerWrapper otherTestWrapper;
    private List<BattleObserver> handlerWrappersMocks;
    private List<Fleet> mockedFleet;
    private Fleet firstMockShips;
    private Fleet secondMockShips;

    @BeforeTest
    public void setUp() {
        firstTestWrapper = mock(HandlerWrapper.class);
        otherTestWrapper = mock(HandlerWrapper.class);
        secondTestWrapper = mock(HandlerWrapper.class);
        firstMockShips = mock(Fleet.class);
        secondMockShips = mock(Fleet.class);
        handlerWrappersMocks = Arrays.asList(firstTestWrapper, secondTestWrapper);
        mockedFleet = Arrays.asList(firstMockShips, secondMockShips);

    }

    @Test
    @Ignore
    public void shouldPassWhenGameStateIsProcessedAndCorrectSalvoResultIsReturned() {
        //TODO: Rework and refactor.
        //given
        //AT THE MOMENT CAN"T BE DONE BETTER! WILL BE REFACTORED IN FUTURE need to do it this way
        //so I will not get immutable lists.
        ArgumentCaptor<Messagable> captor = ArgumentCaptor.forClass(Messagable.class);
        ArrayList<Integer> firstFleet = new ArrayList<>();
        firstFleet.addAll(Arrays.asList(1, 5, 3, 6));
        ArrayList<Integer> secondFleet = new ArrayList<>();
        secondFleet.addAll(Arrays.asList(1, 2));
        ArrayList<Integer> firstSalvo = new ArrayList<>();
        firstSalvo.addAll(Arrays.asList(1, 2));
        ArrayList<Integer> secondSalvo = new ArrayList<>();
        secondSalvo.addAll(Arrays.asList(5, 6));
        when(firstMockShips.getAllPositions()).thenReturn(firstFleet);
        when(secondMockShips.getAllPositions()).thenReturn(secondFleet);
        when(firstTestWrapper.receiveMessage()).thenReturn(new Salvo(firstSalvo));
        when(secondTestWrapper.receiveMessage()).thenReturn(new Salvo(secondSalvo));
        GameInProgress gameInProgress = new GameInProgress(handlerWrappersMocks, mockedFleet);
        //when
        gameInProgress.process();
        verify(firstTestWrapper).sendMessage(captor.capture());
        SalvoResult salvoResult = (SalvoResult) captor.getValue();
        //then
        assertThat(salvoResult.getResultList()).isEqualTo(Arrays.asList(1, 3));
    }

    @Test
    @Ignore
    public void shouldPassWhenEndBooleanIsTrueAfterOneOfFleetsIsDead() {
        //given
        List<Fleet> testFleet = Arrays
                .asList(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3)))
                        , new Fleet(Arrays.asList(Ship.createShip(4, 5, 6))));
        when(otherTestWrapper.receiveMessage()).thenReturn(new Salvo(Arrays.asList(5, 6)));
        when(secondTestWrapper.receiveMessage()).thenReturn(new Salvo(Arrays.asList(1, 2, 3)));
        GameInProgress gameInProgress = new GameInProgress(Arrays.asList(otherTestWrapper, secondTestWrapper), testFleet);
        gameInProgress.process();
        System.out.println(gameInProgress.areAllShipsSunk());
    }
}