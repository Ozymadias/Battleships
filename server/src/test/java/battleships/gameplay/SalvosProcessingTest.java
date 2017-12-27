package battleships.gameplay;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class SalvosProcessingTest {
  private SalvosProcessing salvosProcessing;

  @BeforeTest
  public void setUp() {
    salvosProcessing = new SalvosProcessing(mock(ArrayList.class), mock(ArrayList.class), mock(ArrayList.class));
  }

  @Test
  public void shouldPassWhenSalvoProcessingIsNotEndOfGame() {
    assertThat(salvosProcessing.isEndOfTheGame()).isFalse();
  }
}