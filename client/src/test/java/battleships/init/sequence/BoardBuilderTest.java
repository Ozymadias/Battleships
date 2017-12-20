package battleships.init.sequence;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardBuilderTest {

    @Test
    public void whenBuildingBoardWithCleanFields_listOfFieldsShouldHave100Elements(){
        //given
        BoardForRandom sequencesGenerator = BoardBuilderForRandom.withCleanFields().build();
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
        BoardForRandom board = BoardBuilderForRandom.withCleanFields().build();
        //when - then
        assertThat(board.isFieldEmpty(fieldPosition)).isEqualTo(true);
    }

    @Test
    public void whenBuildingBoardWithCleanFields_printofBoard(){
        //given
        BoardForRandom sequencesGenerator = BoardBuilderForRandom.withCleanFields().build();
        //when - then
        System.out.println(sequencesGenerator.statesMarksToString());
    }

    @Test
    public void whenBuildingBoardWithBorders_printofBoard(){
        //given
        BoardForRandom sequencesGenerator = BoardBuilderForRandom.withBorders().build();
        //when - then
        System.out.println(sequencesGenerator.statesMarksToString());
    }


}