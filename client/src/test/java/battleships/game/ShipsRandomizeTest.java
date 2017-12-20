package battleships.game;

import battleships.game.BoardBuilder;
import battleships.game.ShipsRandomize;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {

    @Test
    public void givenTwoDifferentDrawOfShips_boardsShouldDiffers(){
        ShipsRandomize firstRandomShipsSet = ShipsRandomize.build(BoardBuilder.build());
        firstRandomShipsSet.placeAllFloat();

        ShipsRandomize secondRandomShipsSet = ShipsRandomize.build(BoardBuilder.build());
        secondRandomShipsSet.placeAllFloat();

        assertThat(firstRandomShipsSet.statesMarksToString())
                .isNotEqualToIgnoringCase(secondRandomShipsSet.statesMarksToString());
    }

}