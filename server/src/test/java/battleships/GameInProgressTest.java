package battleships;

import battleships.communication.Messagable;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.mockito.ArgumentCaptor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class GameInProgressTest {
    private HandlerWrapper firstTestWrapper;
    private HandlerWrapper secondTestWrapper;
    private HandlerWrapper otherTestWrapper;
    private List<HandlerWrapper> handlerWrappersMocks;
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
    public void shouldPassWhenGameStateIsProcessedAndCorrectSalvoResultIsReturned() {
        //given
        ArgumentCaptor<Messagable> captor = ArgumentCaptor.forClass(Messagable.class);
        when(firstMockShips.getAllPositions()).thenReturn(Arrays.asList(1, 5, 6, 3));
        when(secondMockShips.getAllPositions()).thenReturn(Arrays.asList(1, 2));
        when(firstTestWrapper.raport()).thenReturn(new Salvo(Arrays.asList(1, 2)));
        when(secondTestWrapper.raport()).thenReturn(new Salvo(Arrays.asList(5, 6)));
        GameInProgress gameInProgress = new GameInProgress(handlerWrappersMocks, mockedFleet);
        //when
        gameInProgress.process();
        verify(firstTestWrapper).getNotified(captor.capture());
        SalvoResult salvoResult = (SalvoResult) captor.getValue();
        System.out.println(salvoResult.getResultList());
        //then
        assertThat(salvoResult.getResultList()).isEqualTo(Arrays.asList(1, 3));
    }

    @Test
    public void shouldPassWhenEndBooleanIsTrueAfterOneOfFleetsIsDead() {
        //given
        List<Fleet> testFleet = Arrays
                .asList(new Fleet(Collections.singletonList(Ship.createShip(1, 2, 3)))
                        , new Fleet(Collections.singletonList(Ship.createShip(4, 5, 6))));
        when(otherTestWrapper.raport()).thenReturn(new Salvo(Arrays.asList(5, 6)));
        when(secondTestWrapper.raport()).thenReturn(new Salvo(Arrays.asList(1, 2, 3)));
        GameInProgress gameInProgress = new GameInProgress(Arrays.asList(otherTestWrapper, secondTestWrapper), testFleet);
        gameInProgress.process();
        System.out.println(gameInProgress.isEndOfTheGame());
    }


}