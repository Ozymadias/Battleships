package battleships.init.sequence;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardTest {

    @Test
    public void whenBuildingBoard_listOfFieldsShouldHave100Elements(){
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
    public void whenBuildingBoard_eachFieldShouldBeEmpty(Integer fieldPosition){
        //given
        Board board = Board.build();
        //when - then
        assertThat(board.isFieldEmpty(fieldPosition)).isEqualTo(true);
    }

}