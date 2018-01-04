package battleships.gameplay;

import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class SinkingShipsTest {

  @DataProvider(name = "fleetAndSalvosData")
  public static Object[][] fleetAndSalvosData() {
    return Triplet.provideTestDataForSinkingShips();
  }

  @Test(dataProvider = "fleetAndSalvosData")
  public void whenSinkingShipIsProcessed_expectFleetStateToMatchExpectedResult(Triplet triplet, int fleetIndex, boolean expectedResult) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet.salvoResults);
    //when
    sinkingShips.process();
    //then
    assertThat(checkIfShipsAreDead(triplet.fleets.get(fleetIndex))).isEqualTo(expectedResult);
  }

  @Test
  public void whenSinkingShipsStateIsProcessed_expectIsEndOfGameToBeFalse() {
    //given
    SinkingShips sinkingShips = new SinkingShips(mock(List.class), mock(List.class), mock(List.class));
    //then
    assertThat(sinkingShips.isEndOfTheGame()).isFalse();
  }

  private boolean checkIfShipsAreDead(Fleet fleet) {
    return fleet.getShips().stream().allMatch(Ship::isSunk);
  }
}