package battleships.ships;

import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FleetTest {

    @Test
    public void shouldPassWhenNumberOfPositionsIsEqualToExpected() {
        Fleet fleet = new Fleet(Arrays.asList(Ship.createShip(1, 2, 3), Ship.createShip(44, 21, 2, 24)));
        assertThat(fleet.getAllPositions().size()).isEqualTo(7);
    }
}