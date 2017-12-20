package battleships.game;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SequenceTest {

    @Test
    public void givenSequenceStartingFrom0Up10_printingPositionsShouldGiveListFrom0To10(){
        //given

    }

    @Test
    public void givenSequenceOfEmptyFields_whenCheckingIfCanContainShipOf4Masts_expectedIsTrue(){
        List<Field> fields = IntStream.iterate(0, i->i+1)
                                        .limit(10)
                                        .mapToObj(p -> new Field(p))
                                        .collect(Collectors.toList());
        SequenceForRandom sequence = new SequenceForRandom(fields);
        assertThat(sequence.canContainShip(4)).isTrue();
    }

}