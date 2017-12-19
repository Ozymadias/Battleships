package battleships.init.sequence;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SequencesGeneratorTest {

    @DataProvider
    private Object[][] horizontalSequencePoll(){
        return new Object[][]{
                {0, "[0][1][2][3][4][5][6][7][8][9]"},
                {10, "[10][11][12][13][14][15][16][17][18][19]"}

        };
    }

    @Test(dataProvider = "horizontalSequencePoll")
    public void givenFirstIndex_whenCreatingHorizontalSequence_thenListOfPositionsShouldBeAsExpected(Integer firstIndex, String expectedList){
        //given
        SequencesGenerator sequencesGenerator = new SequencesGenerator(BoardBuilder.withCleanFields().build());
        Sequence sequence = sequencesGenerator.createHorizontalSequence(firstIndex);
        //when - then
        assertThat(sequence.positionsToString()).isEqualTo(expectedList);
    }

    @DataProvider
    private Object[][] verticalSequencePoll(){
        return new Object[][]{
                {0, "[0][10][20][30][40][50][60][70][80][90]"},
                {5, "[5][15][25][35][45][55][65][75][85][95]"}

        };
    }

    @Test(dataProvider = "verticalSequencePoll")
    public void givenFirstIndex_whenCreatingVerticalSequence_thenListOfPositionsShouldBeAsExpected(Integer firstIndex, String expectedList){
        //given
        SequencesGenerator sequencesGenerator = new SequencesGenerator(BoardBuilder.withCleanFields().build());
        Sequence sequence = sequencesGenerator.createVerticalSequence(firstIndex);
        //when - then
        assertThat(sequence.positionsToString()).isEqualTo(expectedList);
    }

    @Test
    public void printingSequenceFieldsStatesTest(){
        //given
        SequencesGenerator sequencesGenerator = new SequencesGenerator(BoardBuilder.withCleanFields().build());
        Sequence sequence = sequencesGenerator.createVerticalSequence(0);
        //then
        System.out.println(sequence.statesMarksToString());

    }
}