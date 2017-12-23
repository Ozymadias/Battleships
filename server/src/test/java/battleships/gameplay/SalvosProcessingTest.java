package battleships.gameplay;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class SalvosProcessingTest {

    @Test
    public void shouldPassWhenSalvoProcessingIsNotEndOfGame() throws Exception {
        SalvosProcessing salvosProcessing = new SalvosProcessing(mock(ArrayList.class), mock(ArrayList.class), mock(ArrayList.class));
        assertThat(salvosProcessing.isEndOfTheGame()).isFalse();
    }
}