package battleships.ships;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShipTest {

  @Test
  public void whenCreating5MastShip_andKillingTwoMastOfShip_expectedIsSunkToReturnFalse() {
    Ship ship = Ship.createShip(1, 2, 3, 4, 5);
    ship.killMast(2);
    ship.killMast(4);
    assertThat(ship.isSunk()).isFalse();
  }

  @Test
  public void whenCreating3MastShip_andKillingAllMastOfShip_expectedIsSunkToReturnFalse() {
    Ship ship = Ship.createShip(1, 2, 3);
    ship.killMast(1);
    ship.killMast(2);
    ship.killMast(3);
    assertThat(ship.isSunk()).isTrue();
  }
}