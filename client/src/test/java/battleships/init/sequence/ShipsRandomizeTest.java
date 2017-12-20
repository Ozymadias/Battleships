package battleships.init.sequence;

import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {

    @Test
    public void givenTwoDifferentDrawOfShips_boardsShouldDiffers(){
        ShipsRandomize firstRandomShipsSet = ShipsRandomize.build(BoardBuilderForRandom.withBorders().build());
        firstRandomShipsSet.placeAllFloat();

        ShipsRandomize secondRandomShipsSet = ShipsRandomize.build(BoardBuilderForRandom.withBorders().build());
        secondRandomShipsSet.placeAllFloat();

        assertThat(firstRandomShipsSet.statesMarksToString())
                .isNotEqualToIgnoringCase(secondRandomShipsSet.statesMarksToString());
    }

}