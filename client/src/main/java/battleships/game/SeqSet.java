package battleships.game;

import battleships.ships.Ship;

import java.util.Random;

interface SeqSet {

  SeqForRandom get(Integer index);

  default Ship randomlyPlaceShip(Integer sequenceIndex, Integer shipLength) {
    Integer firstEmptyPosition = this.get(sequenceIndex).firstEmptyFor(shipLength);
    Integer lastEmptyPosition = this.get(sequenceIndex).lastEmptyStartingBy(firstEmptyPosition);
    Integer position;
    if (firstEmptyPosition.equals(lastEmptyPosition - shipLength + 1)) {
      position = firstEmptyPosition;
    } else {
      position = new Random()
          .ints(firstEmptyPosition, lastEmptyPosition - shipLength + 1)
          .findFirst()
          .getAsInt();
    }
    return putShipInSequence(sequenceIndex, position, shipLength);
  }

  Ship putShipInSequence(Integer seqIndex, Integer initialRandomPosition, Integer shipLength);
}
