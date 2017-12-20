package battleships.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void whenBuildingBoardWithCleanFields_fieldCountShouldEquals100(){
        Board board = Board.build();
        assertThat(board.getFields().size()).isEqualTo(100);
    }

    @Test
    public void whenBuildingBoardWithCleanFields_countOfEmptyFieldShouldBe100(){
        Board board = Board.build();
        assertThat(board.getFields().stream().filter(Field::isEmpty).count()).isEqualTo(100);
    }

    @Test
    public void whenBuildingBoardWithCleanFields_listOfFieldsShouldHave100Elements(){
        //given
        Board sequencesGenerator = Board.build();
        //when - then
        assertThat(sequencesGenerator.getFields().size()).isEqualTo(100);
    }

    @DataProvider
    public Object[] randomPollOfTenFieldsPositions(){
        return new Random().ints(10, 0, 99).boxed().toArray();
    }

    @Test(dataProvider = "randomPollOfTenFieldsPositions")
    public void whenBuildingBoardWithCleanFields_eachFieldShouldBeEmpty(Integer fieldPosition){
        //given
        Board board = Board.build();
        //when - then
        assertThat(board.getFields().get(fieldPosition).isEmpty()).isTrue();
    }

}