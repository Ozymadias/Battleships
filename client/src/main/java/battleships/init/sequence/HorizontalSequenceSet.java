package battleships.init.sequence;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorizontalSequenceSet implements SequencesSet {

    private List<SequenceForRandom> horizontalSequences;

    private static final int INCREMENTATION = 1;

    public HorizontalSequenceSet(List<SequenceForRandom> horizontalSequences) {
        this.horizontalSequences = horizontalSequences;
    }

    public static HorizontalSequenceSet build(BoardForRandom board) {
        List<SequenceForRandom> sequenceList = IntStream.iterate(0, i -> i+10)
                .limit(SEQUENCE_COUNT)
                .mapToObj(i-> createSingleSequence(i, board))
                .collect(Collectors.toList());
        return new HorizontalSequenceSet(sequenceList);
    }

    private static SequenceForRandom createSingleSequence(Integer first, BoardForRandom board){
        List<Integer> numbersInSequence = IntStream.iterate(first, i->i+INCREMENTATION)
                .limit(SEQUENCE_LENGTH)
                .boxed()
                .collect(Collectors.toList());
        List<FieldForRandom> fields = board.getFields().stream()
                .filter(p -> numbersInSequence.contains(p.getPosition()))
                .collect(Collectors.toList());
        return new SequenceForRandom(fields);
    }

    @Override
    public SequenceForRandom get(Integer index) {
        return horizontalSequences.get(index);
    }

    @Override
    public void putShipIntoSequence(Integer sequenceIndex, Integer firstPositionOfShip, Integer shipLength) {
        assert(firstPositionOfShip+shipLength < 10);
        List<Integer> fieldsIndexesInSequence = IntStream.range(firstPositionOfShip, firstPositionOfShip+shipLength).boxed().collect(Collectors.toList());
        setBufferAround(sequenceIndex, new LinkedList<>(fieldsIndexesInSequence));
        horizontalSequences.get(sequenceIndex).setOccupied(fieldsIndexesInSequence);
    }

    private void setBufferAround(Integer sequenceIndex, LinkedList<Integer> fieldsIndexesInSequence) {
        if(sequenceIndex > 0){
            horizontalSequences.get(sequenceIndex-1).setBuffered(fieldsIndexesInSequence);
        }
        if(sequenceIndex < 10){
            horizontalSequences.get(sequenceIndex+1).setBuffered(fieldsIndexesInSequence);
        }
        if(!horizontalSequences.get(sequenceIndex).isOnBorder(fieldsIndexesInSequence.getFirst())){
            horizontalSequences.get(sequenceIndex).setBuffered(fieldsIndexesInSequence.getFirst()-1);
        }
        if(!horizontalSequences.get(sequenceIndex).isOnBorder(fieldsIndexesInSequence.getLast())){
            horizontalSequences.get(sequenceIndex).setBuffered(fieldsIndexesInSequence.getLast()+1);
        }
    }

    public String statesMarksToString(){
        return horizontalSequences.stream()
                .map(seq -> seq.statesMarksToString())
                .collect(Collectors.joining("\n"));
    }
}
