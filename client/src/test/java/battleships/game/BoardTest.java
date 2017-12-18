package battleships.game;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void whenBuildingBoard_fieldCountShouldEquals100(){
        Board board = Board.build();
        assertThat(board.fields.size()).isEqualTo(100);
    }

    @Test
    public void buildExampleTest(){
        Board board = Board.build();
        board.fields.stream().filter(p -> p.getState().equals(FieldState.EMPTY)).count();
    }

}