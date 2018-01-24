package battleships.gameplay;

import battleships.gameplay.util.Triplet;
import battleships.gameplay.util.TripletDP;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.assertj.core.api.Condition;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;

public class SinkingShipsTest {
  
  @Test(dataProvider = "fleetAndSalvosData", dataProviderClass = TripletDP.class)
  public void whenSinkingShipIsProcessed_expectFleetStateToMatchExpectedResult(Triplet triplet,
                                                                               int fleetIndex,
                                                                               boolean
                                                                                   expectedResult) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet
                                                                                        .salvoResults);
    //when
    sinkingShips.process();
    //then
    assertThat(checkIfShipsAreDead(triplet.fleets.get(fleetIndex))).isEqualTo(expectedResult);
    assertThat(triplet.fleets.get(fleetIndex)).is(deadJava8);
    assertThat(triplet.fleets.get(fleetIndex)).is(deadJava7);
  }
  
  @Test(dataProvider = "fleetOfTheSecondPlayerSunks", dataProviderClass = TripletDP.class)
  public void fleetOfTheSecondPlayerShouldSunk(Triplet triplet) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet
                                                                                        .salvoResults);
    int secondPlayerFleet = 1;
    //when
    sinkingShips.process();
    //then
//    assertTrue(checkIfShipsAreDead(triplet.fleets.get(secondPlayerFleet)));
    assertThat(triplet.fleets.get(secondPlayerFleet)).is(deadJava8);
  }
  
  @Test(dataProvider = "fleetOfTheFirstPlayerSunks", dataProviderClass = TripletDP.class)
  public void fleetOfTheFirstPlayerShouldSunk(Triplet triplet) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet
                                                                                        .salvoResults);
    int firstPlayerFleet = 0;
    //when
    sinkingShips.process();
    //then
//    assertTrue(checkIfShipsAreDead(triplet.fleets.get(firstPlayerFleet)));
    assertThat(triplet.fleets.get(firstPlayerFleet)).is(deadJava7);
  }
  
  @Test(dataProvider = "fleetOfTheFirstPlayerDoesNotSunk", dataProviderClass = TripletDP.class)
  public void fleetOfTheSecondPlayerDoesNotSunk(Triplet triplet) {
    //given
    SinkingShips sinkingShips = new SinkingShips(triplet.observers, triplet.fleets, triplet
                                                                                        .salvoResults);
    int secondPlayerFleet = 0;
    //when
    sinkingShips.process();
    //then
    assertFalse(checkIfShipsAreDead(triplet.fleets.get(secondPlayerFleet)));
    
  }
  
  @Test
  public void whenSinkingShipsStateIsProcessed_expectIsEndOfGameToBeFalse() {
    //given
    SinkingShips sinkingShips = new SinkingShips(mock(List.class), mock(List.class), mock(List.class));
    //then
    assertThat(sinkingShips.isEndOfTheGame()).isFalse();
  }
  
  //original helper method
  private boolean checkIfShipsAreDead(Fleet fleet) {
    return fleet.getShips().stream().allMatch(Ship::isSunk);
  }
  
  //J7
  Condition<Fleet> deadJava7 = new Condition<Fleet>("a ship is sunken") {
    @Override
    public boolean matches(Fleet fleet) {
      return fleet.getShips().stream().allMatch(Ship::isSunk);
    }
  };
  
  //J8
  Condition<Fleet> deadJava8 = new Condition<>(fleet -> fleet.getShips().stream().allMatch
                                                                                      (Ship::isSunk), "a ship is sunken");
}