package battleships.game;

import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SequenceTest {

  private List<Field> getListOfEmptyFields() {
    return IntStream.iterate(0, i -> i + 1)
        .limit(10)
        .mapToObj(Field::new)
        .collect(Collectors.toList());
  }
  @Test
  public void whenCheckingIfSequenceOfEmptyFieldsCanContainShipOf4Masts_expectItCan() {
    //given
    List<Field> fields = getListOfEmptyFields();
    SeqForRandom sequence = new SeqForRandom(fields);
    //when
    boolean canSequenceContainsShip = sequence.canContainShip(4);
    //then
    assertThat(canSequenceContainsShip).isTrue();
  }

  @Test
  public void whenCheckingIfSequenceOfBufferFieldsCanContainShipOf4Masts_expectItCanNot() {
    //given
    List<Field> fields = getListOfEmptyFields();
    fields.forEach(Field::setBuffer);
    SeqForRandom sequence = new SeqForRandom(fields);
    //when
    boolean canSequenceContainsShip = sequence.canContainShip(4);
    //then
    assertThat(canSequenceContainsShip).isFalse();
  }

}