package battleships.init;

import battleships.init.sequence.Sequence;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ShipsRandomizeTest {

    @Test
    public void whenBuildingShipsRandomize_fieldsOfFirstHorizontalSequenceShouldBeEmptyWithPositionsFrom0UpTo9(){
        ShipsRandomize shipsRandomize = ShipsRandomize.build();
        Sequence firstSequence = new LinkedList<>(shipsRandomize.horizontalSequences).getFirst();
        assertThat(firstSequence.toString())
                .isEqualTo("[0 : BORDER][1 : BORDER][2 : BORDER][3 : BORDER][4 : BORDER][5 : BORDER][6 : BORDER][7 : BORDER][8 : BORDER][9 : BORDER]");
    }

    @Test
    public void whenBuildingShipsRandomize_fieldsOfLastHorizontalSequenceShouldBeEmptyWithPositionsFrom90UpTo99(){
        ShipsRandomize shipsRandomize = ShipsRandomize.build();
        Sequence firstSequence = new LinkedList<>(shipsRandomize.horizontalSequences).getLast();
        assertThat(firstSequence.toString())
                .isEqualTo("[90 : BORDER][91 : BORDER][92 : BORDER][93 : BORDER][94 : BORDER][95 : BORDER][96 : BORDER][97 : BORDER][98 : BORDER][99 : BORDER]");
    }

}