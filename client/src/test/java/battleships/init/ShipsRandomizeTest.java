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
                .isEqualTo("[0 : EMPTY][1 : EMPTY][2 : EMPTY][3 : EMPTY][4 : EMPTY][5 : EMPTY][6 : EMPTY][7 : EMPTY][8 : EMPTY][9 : EMPTY]");
    }

    @Test
    public void whenBuildingShipsRandomize_fieldsOfLastHorizontalSequenceShouldBeEmptyWithPositionsFrom90UpTo99(){
        ShipsRandomize shipsRandomize = ShipsRandomize.build();
        Sequence firstSequence = new LinkedList<>(shipsRandomize.horizontalSequences).getLast();
        assertThat(firstSequence.toString())
                .isEqualTo("[90 : EMPTY][91 : EMPTY][92 : EMPTY][93 : EMPTY][94 : EMPTY][95 : EMPTY][96 : EMPTY][97 : EMPTY][98 : EMPTY][99 : EMPTY]");
    }

}