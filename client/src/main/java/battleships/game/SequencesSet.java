package battleships.game;

import battleships.ships.Ship;

import java.util.Random;

interface SequencesSet {

    int SEQUENCE_COUNT = 10;

    SequenceForRandom get(Integer index);

    default Ship randomlyPlaceShip(Integer sequenceIndex, Integer shipLength){
        Integer firstEmptyPosition = this.get(sequenceIndex).firstEmptyFor(shipLength);
        Integer lastEmptyPosition = this.get(sequenceIndex).lastEmptyStartingBy(firstEmptyPosition);
        Integer position;
        if (firstEmptyPosition.equals(lastEmptyPosition - shipLength + 1)) {
            position = firstEmptyPosition;
        } else {
            position = new Random().ints(firstEmptyPosition, lastEmptyPosition - shipLength + 1).findFirst().getAsInt();
        }
        return putShipIntoSequence(sequenceIndex, position, shipLength);
    }

    Ship putShipIntoSequence(Integer sequenceIndex, Integer firstPositionRandomized, Integer shipLength);
}
