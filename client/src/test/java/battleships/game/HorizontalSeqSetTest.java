package battleships.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HorizontalSeqSetTest {

  private HorizontalSeqSet horizontalSeqSet;

  @BeforeMethod
  public void beforeMethod() {
    //given
    Board board = Board.build();
    horizontalSeqSet = HorizontalSeqSet.build(board);
  }

  @Test
  public void givenBoardWithBorders_whenPlacing5MastShipInto3SequenceAtPosition3_stateMarksToStringShouldBeEqualTo_xeboooobex() {
    //when
    horizontalSeqSet.putShipInSequence(3, 3, 4);
    //then
    assertThat(horizontalSeqSet.get(3).statesMarksToString()).isEqualTo("eeboooobee");
  }

  @Test
  public void givenBoardWithBorders_whenPlacing4MastShipInto5SequenceAtRandomSequencePosition_stateMarksToStringShouldBeEqualTo_xeboooobex() {
    //when
    horizontalSeqSet.randomlyPlaceShip(5, 4);
    //then
    assertThat(horizontalSeqSet.get(5).statesMarksToString()).contains("oooo");
  }

}