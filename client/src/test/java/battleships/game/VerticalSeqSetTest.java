package battleships.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

  private List<Integer> getPositionsOfShip() {
    return board.getFields()
        .stream()
        .filter(field -> field.isUnbrokenShipOn())
        .mapToInt(field -> field.getPosition()).boxed().collect(Collectors.toList());
  }

  @DataProvider(name = "placingShips")
  public static Object[][] placingShips() {
    return new Object[][] {
        {2, 1, 4, Arrays.asList(12, 22, 32, 42)},
        {0, 0, 4, Arrays.asList(0, 10, 20, 30)},
        {9, 0, 4, Arrays.asList(9, 19, 29, 39)},
        {0, 7, 3, Arrays.asList(70, 80, 90)},
        {9, 8, 2, Arrays.asList(89, 99)}
    };
  }
  @Test(dataProvider = "placingShips")
  public void whenPuttingShipIntoCertainPositionsOfSequence_expectBoardFieldsAreSetAsContainingShip(
      int sequenceIndex,
      int firstPositionOfShip,
      int shipLength,
      List<Integer> expectedPositionsOfShip
  ) {
    //when
    verticalSeqSet.putShipInSequence(sequenceIndex, firstPositionOfShip, shipLength);
    List<Integer> actualPositionsOfShip = getPositionsOfShip();
    //then
    assertThat(actualPositionsOfShip).isEqualTo(expectedPositionsOfShip);
  }

  private List<Integer> getPositionOfBuffer() {
    return board.getFields()
        .stream()
        .filter(field -> field.isBuffered())
        .mapToInt(field -> field.getPosition()).boxed().collect(Collectors.toList());
  }

  @DataProvider(name = "placingShipsWithBuffer")
  public static Object[][] placingShipsWithBuffer() {
    return new Object[][] {
        {2, 1, 4, Arrays.asList(1, 2, 3, 11, 13, 21, 23, 31, 33, 41, 43, 51, 52, 53)},
        {0, 0, 4, Arrays.asList(1, 11, 21, 31, 40, 41)},
        {9, 0, 4, Arrays.asList(8, 18, 28, 38, 48, 49)},
        {0, 7, 3, Arrays.asList(60, 61, 71, 81, 91)},
        {9, 8, 2, Arrays.asList(78, 79, 88, 98)}
    };
  }

  @Test(dataProvider = "placingShipsWithBuffer")
  public void whenPuttingShipIntoInCertainPositionsOfSequence_expectBoardFieldsAreSetAsBuffer(
      int sequenceIndex,
      int firstPositionOfShip,
      int shipLength,
      List<Integer> expectedPositionsOfBuffer
  ) {
    //when
    verticalSeqSet.putShipInSequence(sequenceIndex, firstPositionOfShip, shipLength);
    List<Integer> actualPositionsOfBuffer = getPositionOfBuffer();
    //then
    assertThat(actualPositionsOfBuffer).isEqualTo(expectedPositionsOfBuffer);
  }


}