package battleships.game;

import org.testng.annotations.Test;

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

}