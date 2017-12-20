package battleships.game;

import battleships.game.SequenceForRandom;

import java.util.Random;

public interface SequencesSet {

    public static final Integer SEQUENCE_LENGTH = 10;
    public static final Integer SEQUENCE_COUNT = 10;

    public SequenceForRandom get(Integer index);

    default void randomlyPlaceShip(Integer sequenceIndex, Integer shipLength){
        Integer firstEmptyPosition = 0, lastEmptyPosition = 0;
        try {
            firstEmptyPosition = this.get(sequenceIndex).firstEmptyFor(shipLength);
            lastEmptyPosition = this.get(sequenceIndex).lastEmptyStartingBy(firstEmptyPosition);
            Integer firstPositionRandomized = new Random().ints(firstEmptyPosition, lastEmptyPosition - shipLength + 1).findFirst().getAsInt();

            putShipIntoSequence(sequenceIndex, firstPositionRandomized, shipLength);
        }catch (IllegalArgumentException e){
            System.out.println("firstEmptyPosition = " + firstEmptyPosition + " lastEmptyPosition = " + lastEmptyPosition + " shipLength = " + shipLength);
        }
    }

    void putShipIntoSequence(Integer sequenceIndex, Integer firstPositionRandomized, Integer shipLength);
}
