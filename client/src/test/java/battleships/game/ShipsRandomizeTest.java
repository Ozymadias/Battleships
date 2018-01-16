package battleships.game;

import battleships.ships.Fleet;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {
  private ShipsRandomize firstRandomShipsSet;
  private Fleet firstFleet;
  private ShipsRandomize secondRandomShipsSet;
  @BeforeTest
  public void setUpShipsRandomize() {
    firstRandomShipsSet = ShipsRandomize.build(Board.build());
    firstFleet = firstRandomShipsSet.placeAllFleet();
    secondRandomShipsSet = ShipsRandomize.build(Board.build());
  }
  @Test
  public void whenRandomizeTwoBoards_expectBoardsAreDifferent() {
    //when
    Board firstBoard = firstRandomShipsSet.getBoard();
    Board secondBoard = secondRandomShipsSet.getBoard();
    //then
    assertThat(firstBoard.getFields())
        .isNotEqualTo(secondBoard.getFields());
  }

  @Test
  public void WhenRandomizeFleet_expectEachPositionOfMastInFleetShouldDiffers() {
    //when
    List<Integer> listOfFleetPositions = firstFleet.getAllPositions();
    Set<Integer> setOfPositions = new HashSet<>(listOfFleetPositions);
    int sizeOfList = listOfFleetPositions.size();
    int sizeOfSet = setOfPositions.size();
    //then
    assertThat(sizeOfList)
        .isEqualTo(sizeOfSet);
  }

}