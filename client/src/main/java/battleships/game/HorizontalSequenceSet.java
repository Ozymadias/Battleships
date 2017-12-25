package battleships.game;

import battleships.ships.Ship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorizontalSequenceSet implements SequencesSet {

    private final List<SequenceForRandom> horizontalSequences;

    private static final int STEP_OF_FIELDS_IN_SEQUENCE = 1;
    private static final int SEQUENCE_STEP = 1;
    private static final int SEQUENCE_LENGTH = 10;
    private static final int FIRST_SEQUENCE_INDEX = 0;

    private HorizontalSequenceSet(List<SequenceForRandom> horizontalSequences) {
        this.horizontalSequences = horizontalSequences;
    }

    public static HorizontalSequenceSet build(Board board) {
        List<SequenceForRandom> sequenceList = IntStream.iterate(0, i -> i+SEQUENCE_LENGTH)
                .limit(SEQUENCE_COUNT)
                .mapToObj(i-> createSingleSequence(i, board))
                .collect(Collectors.toList());
        return new HorizontalSequenceSet(sequenceList);
    }

    private static SequenceForRandom createSingleSequence(Integer first, Board board){
        List<Integer> numbersInSequence = IntStream.iterate(first, i->i+STEP_OF_FIELDS_IN_SEQUENCE)
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
        return horizontalSequences.get(index);
    }

    @Override
    public Ship putShipIntoSequence(Integer sequenceIndex, Integer firstPositionOfShip, Integer shipLength) {
        List<Integer> fieldsIndexesInSequence = IntStream.range(firstPositionOfShip, firstPositionOfShip+shipLength).boxed().collect(Collectors.toList());
        setBufferAround(sequenceIndex, new LinkedList<>(fieldsIndexesInSequence));
        return horizontalSequences.get(sequenceIndex).setShip(fieldsIndexesInSequence);
    }

    private void setBufferAround(Integer sequenceIndex, LinkedList<Integer> fieldsIndexesInSequence) {
        if(sequenceIndex > FIRST_SEQUENCE_INDEX){
            horizontalSequences.get(sequenceIndex-SEQUENCE_STEP).setBuffered(fieldsIndexesInSequence);
        }
        int lastSequenceIndex = horizontalSequences.size() - SEQUENCE_STEP;
        if(sequenceIndex < lastSequenceIndex){
            horizontalSequences.get(sequenceIndex+SEQUENCE_STEP).setBuffered(fieldsIndexesInSequence);
        }
        if(!BordersCheck.isOnLeftBorder(fieldsIndexesInSequence.getFirst())){
            horizontalSequences.get(sequenceIndex).setBuffered(fieldsIndexesInSequence.getFirst()-STEP_OF_FIELDS_IN_SEQUENCE);
        }
        if(!BordersCheck.isOnRightBorder(fieldsIndexesInSequence.getLast())){
            horizontalSequences.get(sequenceIndex).setBuffered(fieldsIndexesInSequence.getLast()+STEP_OF_FIELDS_IN_SEQUENCE);
        }
    }
}
