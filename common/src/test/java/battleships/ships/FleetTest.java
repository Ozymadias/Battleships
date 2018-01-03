package battleships.ships;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FleetTest {

  @Test
  public void whenFleetIsCreated_expectFleetHasCorrectSize() {
    //when
    Fleet fleet = new Fleet(Arrays.asList(Ship.createShip(1, 2, 3), Ship.createShip(44, 21, 2, 24)));
    //then
    int fleetSize = fleet.getAllPositions().size();
    assertThat(fleetSize).isEqualTo(7);
  }
}