package battleships.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HorizontalSequenceSetTest {

    private HorizontalSequenceSet horizontalSequenceSet;

    @BeforeMethod
    public void beforeMethod(){
        //given
        Board board = BoardBuilder.withBorders().build();
        horizontalSequenceSet = HorizontalSequenceSet.build(board);
    }

    @Test
    public void givenBoardWithBorders_whenPlacing5MastShipInto3SequenceAtPosition3_stateMarksToStringShouldBeEqualTo_xeboooobex(){
        //when
        horizontalSequenceSet.putShipIntoSequence(3, 3, 4);
        //then
        assertThat(horizontalSequenceSet.get(3).statesMarksToString()).isEqualTo("eeboooobee");
    }

    @Test
    public void givenBoardWithBorders_whenPlacing4MastShipInto5SequenceAtRandomSequencePosition_stateMarksToStringShouldBeEqualTo_xeboooobex(){
        //when
        horizontalSequenceSet.randomlyPlaceShip(5, 4);
        //then
        assertThat(horizontalSequenceSet.get(5).statesMarksToString()).contains("oooo");
    }

}