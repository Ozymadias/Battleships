package battleships.init.sequence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequencesGenerator {

    private static final int SEQUENCE_LENGTH = 10;

    private final Board board;

    SequencesGenerator(Board board) {
        this.board = board;
    }

    public static SequencesGenerator build(){
        return new SequencesGenerator(Board.build());
    }

    private Sequence createBoardElementsSequence(Integer first, Integer incrementation){
        List<Integer> numbersInSequence = IntStream.iterate(first, i->i+incrementation)
                .limit(SEQUENCE_LENGTH)
                .boxed()
                .collect(Collectors.toList());
        List<Field> fields = board.getFields().stream()
                .filter(p -> numbersInSequence.contains(p.getPosition()))
                .collect(Collectors.toList());
        return new Sequence(fields);
    }

    public Sequence createHorizontalSequence(Integer first){
        return createBoardElementsSequence(first, 1);
    }

    public Sequence createVerticalSequence(Integer first){
        return createBoardElementsSequence(first, 10);
    }

}
