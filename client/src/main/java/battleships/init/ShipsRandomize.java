package battleships.init;

import battleships.init.sequence.Sequence;
import battleships.init.sequence.SequencesGenerator;

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

    private void placeShip(int length){
        new Random().ints(0,100);
    }
}
