package battleships.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VerticalSeqSetTest {

  private VerticalSeqSet verticalSeqSet;
  private Board board;

  @BeforeMethod
  public void beforeMethod() {
    board = Board.build();
    verticalSeqSet = VerticalSeqSet.build(board);
  }

  @Test
  public void givenSequenceIndexAndFirstPositionsOfShipAndShipLength_whenPuttingShipIntoInCertainPositionsOfSequence_suitableBoardFieldShouldBeSetAsContainingShip() {
    //given
    Integer sequenceIndex = 2;
    Integer firstPositionOfShip = 1;
    Integer shipLength = 4;
    //when
    verticalSeqSet.putShipInSequence(sequenceIndex, firstPositionOfShip, shipLength);
    List<Integer> expectedPositionsOfShip = Arrays.asList(12, 22, 32, 42);
    //then
    List<Integer> actualPositionsOfShip = board.getFields()
        .stream()
        .filter(Field::isUnbrokenShipOn)
        .mapToInt(Field::getPosition).boxed().collect(Collectors.toList());
    assertThat(actualPositionsOfShip).isEqualTo(expectedPositionsOfShip);
  }

  @Test
  public void givenSequenceIndexAndFirstPositionsOfShipAndShipLength_whenPuttingShipIntoInCertainPositionsOfSequence_suitableBoardFieldShouldBeSetAsBuffer() {
    //given
    Integer sequenceIndex = 2;
    Integer firstPositionOfShip = 1;
    Integer shipLength = 4;
    //when
    verticalSeqSet.putShipInSequence(sequenceIndex, firstPositionOfShip, shipLength);
    List<Integer> expectedPositionsOfBuffer = Arrays.asList(1, 2, 3, 11, 13, 21, 23, 31, 33, 41, 43, 51, 52, 53);
    //then
    List<Integer> actualPositionsOfBuffer = board.getFields()
        .stream()
        .filter(Field::isBuffered)
        .mapToInt(Field::getPosition).boxed().collect(Collectors.toList());
    assertThat(actualPositionsOfBuffer).isEqualTo(expectedPositionsOfBuffer);
  }


}