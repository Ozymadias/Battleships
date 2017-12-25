package battleships.game;

import battleships.ships.Ship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VerticalSequenceSet implements SequencesSet{

    private final List<SequenceForRandom> verticalSequences;

    private static final int STEP_OF_FIELDS_POSITIONS_IN_SEQUENCE = 10;
    private static final int SEQUENCE_STEP = 1;
    private static final int SEQUENCE_LENGTH = 10;
    private static final int FIRST_SEQUENCE_INDEX = 0;
    private static final int FIELD_INDEX_INCREMENTATION = 1;

    public VerticalSequenceSet(List<SequenceForRandom> verticalSequences) {
        this.verticalSequences = verticalSequences;
    }

    public static VerticalSequenceSet build(Board board) {
        List<SequenceForRandom> sequenceList = IntStream.iterate(0, i -> i+SEQUENCE_STEP)
                .limit(SEQUENCE_COUNT)
                .mapToObj(i-> createSingleSequence(i, board))
                .collect(Collectors.toList());
        return new VerticalSequenceSet(sequenceList);
    }

    private static SequenceForRandom createSingleSequence(int first, Board board) {
        List<Integer> numbersInSequence = IntStream.iterate(first, i->i+STEP_OF_FIELDS_POSITIONS_IN_SEQUENCE)
                .limit(SEQUENCE_LENGTH)
                .boxed()
                .collect(Collectors.toList());
        List<Field> fields = board.getFields().stream()
                .filter(p -> numbersInSequence.contains(p.getPosition()))
                .collect(Collectors.toList());
        return new SequenceForRandom(fields);
    }

    @Override
    public SequenceForRandom get(Integer index) {
        return verticalSequences.get(index);
    }

    @Override
    public Ship putShipIntoSequence(Integer sequenceIndex, Integer firstPositionOfShip, Integer shipLength) {
        List<Integer> fieldsIndexesInSequence
                = IntStream.range(firstPositionOfShip, firstPositionOfShip+shipLength)
                            .boxed()
                            .collect(Collectors.toList());
        setBufferAround(sequenceIndex, new LinkedList<>(fieldsIndexesInSequence));
        return verticalSequences.get(sequenceIndex).setShip(fieldsIndexesInSequence);
    }

    private void setBufferAround(Integer sequenceIndex, LinkedList<Integer> fieldsIndexesInSequence) {
        if(sequenceIndex > FIRST_SEQUENCE_INDEX){
            verticalSequences.get(sequenceIndex-SEQUENCE_STEP).setBuffered(fieldsIndexesInSequence);
        }
        int lastSequenceIndex = verticalSequences.size() - SEQUENCE_STEP;
        if(sequenceIndex < lastSequenceIndex){
            verticalSequences.get(sequenceIndex+SEQUENCE_STEP).setBuffered(fieldsIndexesInSequence);
        }

        if(!BordersCheck.isOnLeftBorder(fieldsIndexesInSequence.getFirst())){
            verticalSequences.get(sequenceIndex).setBuffered(fieldsIndexesInSequence.getFirst()-FIELD_INDEX_INCREMENTATION);
        }
        if(!BordersCheck.isOnRightBorder(fieldsIndexesInSequence.getLast())){
            verticalSequences.get(sequenceIndex).setBuffered(fieldsIndexesInSequence.getLast()+FIELD_INDEX_INCREMENTATION);
        }
    }
}
