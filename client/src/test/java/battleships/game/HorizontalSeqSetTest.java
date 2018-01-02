package battleships.game;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

  @DataProvider(name = "placingShipsWithCorrectData")
  public static Object[][] placingShipsWithCorrectData() {
    return new Object[][] {
        {0, 3, 4, "eeboooobee"},
        {0, 0, 4, "oooobeeeee"},
        {0, 6, 4, "eeeeeboooo"},
        {9, 3, 3, "eebooobeee"},
        {9, 0, 3, "ooobeeeeee"},
        {9, 7, 3, "eeeeeebooo"},
        {5, 3, 2, "eeboobeeee"},
        {5, 0, 2, "oobeeeeeee"},
        {5, 8, 2, "eeeeeeeboo"},
        {4, 3, 1, "eebobeeeee"},
        {4, 0, 1, "obeeeeeeee"},
        {4, 9, 1, "eeeeeeeebo"}
    };
  }

  @Test(dataProvider = "placingShipsWithCorrectData")
  public void whenPlacingShipIntoSequence_expectStateMarksToStringIsEqualExpectedString(
      int sequenceIndex,
      int firstPositionOfShip,
      int shipLength,
      String expectedString
  ) {
    //when
    horizontalSeqSet.putShipInSequence(sequenceIndex, firstPositionOfShip, shipLength);
    String statesMarks = horizontalSeqSet.get(sequenceIndex).statesMarksToString();
    //then
    assertThat(statesMarks).isEqualTo(expectedString);
  }

  @DataProvider(name = "placingShipsWithIncorrectData")
  public static Object[][] placingShipsWithIncorrectData() {
    return new Object[][] {
        {0, 7, 4},
        {0, -1, 4},
        {-1, 6, 4},
        {10, 3, 3}
    };
  }
  @Test(dataProvider = "placingShipsWithIncorrectData", expectedExceptions =IndexOutOfBoundsException.class)
  public void whenPlacingShipIntoSequence_expectIndexOutOfBoundExceptionIsThrown(
      int sequenceIndex,
      int firstPositionOfShip,
      int shipLength
  ) {
    //when
    horizontalSeqSet.putShipInSequence(sequenceIndex, firstPositionOfShip, shipLength);
  }

  @DataProvider(name = "randomlyPlacingShipsWithCorrectData")
  public static Object[][] randomlyPlacingShipsWithCorrectData() {
    return new Object[][] {
        {0, 4, "oooo"},
        {2, 3, "ooo"},
        {5, 2, "oo"},
        {9, 1, "o"}
    };
  }
  @Test(dataProvider = "randomlyPlacingShipsWithCorrectData")
  public void whenPlacingShipIntoSequenceAtRandomPosition_expectStateMarksToStringContainsExpectedString(
      int sequenceIndex,
      int shipLength,
      String expectedString
  ) {
    //when
    horizontalSeqSet.randomlyPlaceShip(sequenceIndex, shipLength);
    //then
    String statesMarks = horizontalSeqSet.get(sequenceIndex).statesMarksToString();
    assertThat(statesMarks).contains(expectedString);
  }

}