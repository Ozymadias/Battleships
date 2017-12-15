package battleships.init;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequencesGenerator {

    private static final int SEQUENCE_COUNT = 10;

    List<Sequence> createHorizontalSequences(){
        List<Sequence> sequences = new ArrayList<>(SEQUENCE_COUNT);

        int[] sequencesFirsts = IntStream.iterate(0,(int i) -> i+10 ).limit(10).toArray();

        for(int first : sequencesFirsts){
            List<BoardElement> sequenceList = IntStream.range(first, first+10).mapToObj(p->p+1).map(p -> new BoardElement(p, ShipsRandomize.ElementState.EMPTY)).collect(Collectors.toList());
            sequences.add(new Sequence(sequenceList));

//            for(ShipsRandomize.BoardElement position : sequence){
//                sequenceMap.put(position.position, new ShipsRandomize.Sequence(sequence));
//            }
        }
        return sequences;
    }

    private List<BoardElement> createBoardElementsSequence(Integer first, Integer incrementation){
        return  IntStream.iterate(first,i->i+incrementation)
                .mapToObj(p->new BoardElement(p, ShipsRandomize.ElementState.EMPTY)).limit(10)
                .collect(Collectors.toList());


    }

    List<BoardElement> createHorizontalSequence(Integer first){
        return createBoardElementsSequence(first, 1);
    }

    List<BoardElement> createVerticalSequence(Integer first){
        return createBoardElementsSequence(first, 10);
    }

}
