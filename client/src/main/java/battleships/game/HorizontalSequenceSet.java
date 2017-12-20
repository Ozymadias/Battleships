package battleships.game;

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

    public static HorizontalSequenceSet build(Board board) {
        List<SequenceForRandom> sequenceList = IntStream.iterate(0, i -> i+10)
                .limit(SEQUENCE_COUNT)
                .mapToObj(i-> createSingleSequence(i, board))
                .collect(Collectors.toList());
        return new HorizontalSequenceSet(sequenceList);
    }

    private static SequenceForRandom createSingleSequence(Integer first, Board board){
        List<Integer> numbersInSequence = IntStream.iterate(first, i->i+INCREMENTATION)
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
    public void putShipIntoSequence(Integer sequenceIndex, Integer firstPositionOfShip, Integer shipLength) {
        List<Integer> fieldsIndexesInSequence = IntStream.range(firstPositionOfShip, firstPositionOfShip+shipLength).boxed().collect(Collectors.toList());
        //sprawdzić naroża
        LinkedList<Integer> fieldsIndexesInSequence2 = new LinkedList<>(fieldsIndexesInSequence);
//        if(cornerChecks(sequenceIndex, fieldsIndexesInSequence2)) {
            setBufferAround(sequenceIndex, new LinkedList<>(fieldsIndexesInSequence));
            horizontalSequences.get(sequenceIndex).setShip(fieldsIndexesInSequence);
//        }
    }

    private boolean cornerChecks(Integer sequenceIndex, LinkedList<Integer> fieldsIndexesInSequence2) {
        Integer first = fieldsIndexesInSequence2.getFirst();
        Integer last = fieldsIndexesInSequence2.getLast();
        if(!horizontalSequences.get(sequenceIndex).isOnBorder(first)
                && !horizontalSequences.get(sequenceIndex).isOnBorder(last)){
            return true;
        }else{
            return false;
        }
    }

    private boolean cornersBefore(Integer sequenceIndex, Integer firstPosition){
        return (horizontalSequences.get(sequenceIndex-1).isOnBuffer(firstPosition-1)
                || horizontalSequences.get(sequenceIndex+1).isOnBuffer(firstPosition-1));
    }

    private boolean cornersAfter(Integer sequenceIndex, Integer firstPosition){
        return (horizontalSequences.get(sequenceIndex-1).isOnBuffer(firstPosition+1)
                || horizontalSequences.get(sequenceIndex+1).isOnBuffer(firstPosition+1));
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
