package battleships.gameplay;

import battleships.gameplay.util.Triplet;
import battleships.gameplay.util.TripletDP;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertTrue;

public class SinkingShipsTest {
  
  @Test(dataProvider = "fleetAndSalvosData", dataProviderClass = TripletDP.class)
  public void whenSinkingShipIsProcessed_expectFleetStateToMatchExpectedResult(Triplet triplet, int fleetIndex, boolean expectedResult) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet.salvoResults);
    //when
    sinkingShips.process();
    //then
    assertThat(checkIfShipsAreDead(triplet.fleets.get(fleetIndex))).isEqualTo(expectedResult);
  }
  
  @Test(dataProvider = "fleetOfTheSecondPlayerSunks", dataProviderClass = TripletDP.class)
  public void fleetOfTheSecondPlayerShouldSunk(Triplet triplet) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet.salvoResults);
    int secondPlayerFleet = 1;
    //when
    sinkingShips.process();
    //then
    assertTrue(checkIfShipsAreDead(triplet.fleets.get(secondPlayerFleet)));
  }
  
  @Test(dataProvider = "fleetOfTheFirstPlayerSunks", dataProviderClass = TripletDP.class)
  public void fleetOfTheFirstPlayerShouldSunk(Triplet triplet) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet.salvoResults);
    int firstPlayerFleet = 0;
    //when
    sinkingShips.process();
    //then
    assertTrue(checkIfShipsAreDead(triplet.fleets.get(firstPlayerFleet)));
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