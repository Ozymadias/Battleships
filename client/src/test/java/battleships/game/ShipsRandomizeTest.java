package battleships.game;

import battleships.game.BoardBuilder;
import battleships.game.ShipsRandomize;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {

    @Test
    public void givenTwoDifferentDrawOfShips_boardsShouldDiffers(){
        ShipsRandomize firstRandomShipsSet = ShipsRandomize.build(BoardBuilder.withBorders().build());
        firstRandomShipsSet.placeAllFloat();

        ShipsRandomize secondRandomShipsSet = ShipsRandomize.build(BoardBuilder.withBorders().build());
        secondRandomShipsSet.placeAllFloat();

        assertThat(firstRandomShipsSet.statesMarksToString())
                .isNotEqualToIgnoringCase(secondRandomShipsSet.statesMarksToString());
    }

}