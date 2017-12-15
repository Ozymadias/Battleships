package battleships.init;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class SequencesGeneratorTest {

    @DataProvider
    private Object[][] horizontalSequencePoll(){
        return new Object[][]{
                {0, "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]"},
                {10, "[10, 11, 12, 13, 14, 15, 16, 17, 18, 19]"}

        };
    }

    @Test(dataProvider = "horizontalSequencePoll")
    public void givenFirst_whenCreatingHorizontalSequence_thenListOfPositionsShouldBeAsExpected(Integer first, String expectedList){
        //given
        List<BoardElement> sequence = new SequencesGenerator().createHorizontalSequence(first);
        //when
        List<Integer> positions = sequence.parallelStream().map(p -> p.getPosition()).collect(Collectors.toList());
        //then
        assertEquals(positions.toString(), expectedList);
    }

    @DataProvider
    private Object[][] verticalSequencePoll(){
        return new Object[][]{
                {0, "[0, 10, 20, 30, 40, 50, 60, 70, 80, 90]"},
                {10, "[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]"}

        };
    }

    @Test(dataProvider = "verticalSequencePoll")
    public void givenFirst_whenCreatingVerticalSequence_thenListOfPositionsShouldBeAsExpected(Integer first, String expectedList){
        //given
        List<BoardElement> sequence = new SequencesGenerator().createVerticalSequence(first);
        //when
        List<Integer> positions = sequence.parallelStream().map(p -> p.getPosition()).collect(Collectors.toList());
        //then
        assertEquals(positions.toString(), expectedList);
    }

}