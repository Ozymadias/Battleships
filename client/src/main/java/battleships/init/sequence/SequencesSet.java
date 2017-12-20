package battleships.init.sequence;

import java.util.Random;

public interface SequencesSet {

    public static final Integer SEQUENCE_LENGTH = 10;
    public static final Integer SEQUENCE_COUNT = 10;

    public SequenceForRandom get(Integer index);

    default void randomlyPlaceShip(Integer sequenceIndex, Integer shipLength){
        Integer firstEmptyPosition = this.get(sequenceIndex).firstEmptyFor(shipLength);
        Integer lastEmptyPosition = this.get(sequenceIndex).lastEmptyStartingBy(firstEmptyPosition);
        Integer firstPositionRandomized = new Random().ints(firstEmptyPosition, lastEmptyPosition-shipLength+1).findFirst().getAsInt();
        assert(firstEmptyPosition < 10);
        assert(lastEmptyPosition < 10);
        assert(firstPositionRandomized < 10);
        putShipIntoSequence(sequenceIndex, firstPositionRandomized, shipLength);
    }

    void putShipIntoSequence(Integer sequenceIndex, Integer firstPositionRandomized, Integer shipLength);
}
