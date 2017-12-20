package battleships.game;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void whenBuildingBoardWithCleanFields_fieldCountShouldEquals100(){
        Board board = BoardBuilder.build();
        assertThat(board.fields.size()).isEqualTo(100);
    }

    @Test
    public void whenBuildingBoardWithCleanFields_countOfEmptyFieldShouldBe100(){
        Board board = BoardBuilder.build();
        assertThat(board.fields.stream().filter(p -> p.getState().equals(FieldState.EMPTY)).count()).isEqualTo(100);
    }

}