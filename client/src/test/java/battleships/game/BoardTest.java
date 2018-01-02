package battleships.game;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

  @Test
  public void whenBuildingBoardWithCleanFields_expectFieldCountEquals100() {
    //when
    Board board = Board.build();
    int sizeOfFields = board.getFields().size();
    //then
    assertThat(sizeOfFields).isEqualTo(100);
  }

  @Test
  public void whenBuildingBoardWithCleanFields_expectCountOfEmptyFieldIs100() {
    //when
    Board board = Board.build();
    long sizeOfEmptyFields = board.getFields().stream().filter(Field::isEmpty).count();
    //then
    assertThat(sizeOfEmptyFields).isEqualTo(100);
  }
}