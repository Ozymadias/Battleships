package battleships.game;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

  @Test
  public void whenBuildingBoardWithCleanFields_fieldCountShouldEquals100() {
    Board board = Board.build();
    assertThat(board.getFields().size()).isEqualTo(100);
  }

  @Test
  public void whenBuildingBoardWithCleanFields_countOfEmptyFieldShouldBe100() {
    Board board = Board.build();
    assertThat(board.getFields().stream().filter(Field::isEmpty).count()).isEqualTo(100);
  }

  @Test
  public void whenBuildingBoardWithCleanFields_listOfFieldsShouldHave100Elements() {
    //given
    Board sequencesGenerator = Board.build();
    //when - then
    assertThat(sequencesGenerator.getFields().size()).isEqualTo(100);
  }

}