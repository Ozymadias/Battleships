package battleships.game;

import java.util.Random;

public interface SequencesSet {

    public static final Integer SEQUENCE_LENGTH = 10;
    public static final Integer SEQUENCE_COUNT = 10;

    public SequenceForRandom get(Integer index);

    default void randomlyPlaceShip(Integer sequenceIndex, Integer shipLength){
        try {
            Integer firstEmptyPosition = 0, lastEmptyPosition = 0, position = 0;
            firstEmptyPosition = this.get(sequenceIndex).firstEmptyFor(shipLength);
            lastEmptyPosition = this.get(sequenceIndex).lastEmptyStartingBy(firstEmptyPosition);
            if (firstEmptyPosition.equals(lastEmptyPosition - shipLength + 1)) {
                position = firstEmptyPosition;
            } else {
                position = new Random().ints(firstEmptyPosition, lastEmptyPosition - shipLength + 1).findFirst().getAsInt();
            }
            putShipIntoSequence(sequenceIndex, position, shipLength);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void putShipIntoSequence(Integer sequenceIndex, Integer firstPositionRandomized, Integer shipLength);
}
