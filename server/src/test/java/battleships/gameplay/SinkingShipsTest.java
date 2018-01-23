package battleships.gameplay;

import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@Test
public class SinkingShipsTest {


  @Test(dataProvider = "fleetAndSalvosData", dataProviderClass = TestDPFleetsAndSalvos.class)
  public void whenSinkingShipIsProcessed_expectFleetStateToMatchExpectedResult(Triplet triplet, int fleetIndex, boolean expectedResult) {
    // given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet.salvoResults);
    final Fleet fleet = triplet.fleets.get(fleetIndex);
    // when
    sinkingShips.process();
    // then
    assertThat(checkIfShipsAreDead(fleet)).isEqualTo(expectedResult);
  }

  public void notEndOfGameIfSinkingShipsStateProcessed() {
    // given
    final List mockedList = mock(List.class);
    SinkingShips sinkingShips = new SinkingShips(mockedList, mockedList, mockedList);
    // when
    sinkingShips.process();
    // then
    assertThat(sinkingShips.isEndOfTheGame()).isFalse();
  }

  private boolean checkIfShipsAreDead(Fleet fleet) {
    return fleet.getShips().stream().allMatch(Ship::isSunk);
  }
}