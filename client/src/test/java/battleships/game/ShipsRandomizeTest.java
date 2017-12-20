package battleships.game;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {

    @Test
    public void givenTwoDifferentDrawOfShips_boardsShouldDiffers(){
        ShipsRandomize firstRandomShipsSet = ShipsRandomize.build(Board.build());
        Board firstBoard = firstRandomShipsSet.placeAllFloat();

        ShipsRandomize secondRandomShipsSet = ShipsRandomize.build(Board.build());
        Board secondBoard = secondRandomShipsSet.placeAllFloat();

        assertThat(firstBoard.getFields())
                .isNotEqualTo(secondBoard.getFields());
    }

}