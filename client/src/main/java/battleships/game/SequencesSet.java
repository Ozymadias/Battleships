package battleships.game;

import java.util.Random;

interface SequencesSet {

    public static final int SEQUENCE_COUNT = 10;
    public static final int SEQUENCE_LENGTH = 10;

    public SequenceForRandom get(Integer index);

    default void randomlyPlaceShip(Integer sequenceIndex, Integer shipLength){
        Integer firstEmptyPosition = this.get(sequenceIndex).firstEmptyFor(shipLength);
        Integer lastEmptyPosition = this.get(sequenceIndex).lastEmptyStartingBy(firstEmptyPosition);
        Integer position;
        if (firstEmptyPosition.equals(lastEmptyPosition - shipLength + 1)) {
            position = firstEmptyPosition;
        } else {
            position = new Random().ints(firstEmptyPosition, lastEmptyPosition - shipLength + 1).findFirst().getAsInt();
        }
        putShipIntoSequence(sequenceIndex, position, shipLength);
    }

    void putShipIntoSequence(Integer sequenceIndex, Integer firstPositionRandomized, Integer shipLength);
}
