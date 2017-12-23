package battleships.game;

import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {

    @Test
    public void givenTwoDifferentDrawOfShips_boardsShouldDiffers(){
        ShipsRandomize firstRandomShipsSet = ShipsRandomize.build(Board.build());
        firstRandomShipsSet.placeAllFleet();
        Board firstBoard = firstRandomShipsSet.getBoard();

        ShipsRandomize secondRandomShipsSet = ShipsRandomize.build(Board.build());
        secondRandomShipsSet.placeAllFleet();
        Board secondBoard = secondRandomShipsSet.getBoard();

        assertThat(firstBoard.getFields())
                .isNotEqualTo(secondBoard.getFields());
    }

    @Test
    public void givenBroadWhenRandomizeFleet_eachPositionOfMastInFleetShouldDiffers(){
        ShipsRandomize firstRandomShipsSet = ShipsRandomize.build(Board.build());
        Fleet fleet = firstRandomShipsSet.placeAllFleet();

        Set<Integer> setOfPositions = new HashSet<>(fleet.getAllPositions());

        assertThat(fleet.getAllPositions().size())
                .isEqualTo(setOfPositions.size());
    }

}