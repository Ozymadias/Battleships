package battleships.init;

import battleships.init.sequence.Sequence;
import battleships.init.sequence.SequencesGenerator;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ShipsRandomize {

    private static final int SEQUENCE_COUNT = 10;

    List<Sequence> horizontalSequences;

    public ShipsRandomize(List<Sequence> horizontalSequences) {
        this.horizontalSequences = horizontalSequences;
    }

    static ShipsRandomize build(){
        SequencesGenerator sequencesGenerator = SequencesGenerator.build();
        List<Sequence> horizontalSequences = IntStream.iterate(0, i -> i+10)
                .limit(SEQUENCE_COUNT)
                .mapToObj(i-> sequencesGenerator.createHorizontalSequence(i))
                .collect(Collectors.toList());
        return new ShipsRandomize(horizontalSequences);
    }

    private void placeShipHorizontally(int length){
        Integer randomRow = new Random().nextInt(10);

        //sprawdzenie czy w danym wierszu są wogóle wolne miejsca
        horizontalSequences.get(randomRow).canContainShip(length);

        //pierwszy wolny
        Integer first = horizontalSequences.get(randomRow).firstEmptyFor(length);

        //ostatni z ciągu
        Integer last = horizontalSequences.get(randomRow).lastEmptyStartingBy(first);

        //losowanie położenia w wierszu z przedziału
        Integer randomPositionInRow = new Random().ints(first, last-length+1).findFirst().getAsInt();




    }
}
