package battleships.init.sequence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequencesGenerator {

    private static final int SEQUENCE_LENGTH = 10;

    private final BoardForRandom board;

    SequencesGenerator(BoardForRandom board) {
        this.board = board;
    }

    public static SequencesGenerator build(){
        BoardForRandom board = BoardBuilderForRandom.withBorders().build();
        return new SequencesGenerator(board);
    }

    private SequenceForRandom createBoardElementsSequence(Integer first, Integer incrementation){
        List<Integer> numbersInSequence = IntStream.iterate(first, i->i+incrementation)
                .limit(SEQUENCE_LENGTH)
                .boxed()
                .collect(Collectors.toList());
        List<FieldForRandom> fields = board.getFields().stream()
                .filter(p -> numbersInSequence.contains(p.getPosition()))
                .collect(Collectors.toList());
        return new SequenceForRandom(fields);
    }

    public SequenceForRandom createHorizontalSequence(Integer first){
        return createBoardElementsSequence(first, 1);
    }

    public SequenceForRandom createVerticalSequence(Integer first){
        return createBoardElementsSequence(first, 10);
    }

}
