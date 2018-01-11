package battleships.game;

import static battleships.game.SeqCount.DEFAULT;

import battleships.ships.Ship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class HorizontalSeqSet implements SeqSet {

  private final List<SeqForRandom> horizontalSequences;

  private static final int STEP_OF_FIELDS_IN_SEQUENCE = 1;
  private static final int SEQUENCE_STEP = 1;
  private static final int SEQUENCE_LENGTH = 10;
  private static final int FIRST_SEQUENCE_INDEX = 0;

  private HorizontalSeqSet(List<SeqForRandom> horizontalSequences) {
    this.horizontalSequences = horizontalSequences;
  }

  /**
   * Generate list of all possible horizontal sequences for given board.
   *
   * @param board board that game will be played on.
   * @return list of all possible horizontal sequences.
   */
  public static HorizontalSeqSet build(Board board) {
    List<SeqForRandom> sequenceList = IntStream.iterate(0, i -> i + SEQUENCE_LENGTH)
        .limit(DEFAULT.getValue())
        .mapToObj(i -> createSingleSequence(i, board))
        .collect(Collectors.toList());
    return new HorizontalSeqSet(sequenceList);
  }

  private static SeqForRandom createSingleSequence(Integer first, Board board) {
    List<Integer> numbersInSequence = IntStream.iterate(first, i -> i + STEP_OF_FIELDS_IN_SEQUENCE)
        .limit(SEQUENCE_LENGTH)
        .boxed()
        .collect(Collectors.toList());
    List<Field> fields = board.getFields().stream()
        .filter(p -> numbersInSequence.contains(p.getPosition()))
        .collect(Collectors.toList());
    return new SeqForRandom(fields);
  }

  @Override
  public SeqForRandom get(Integer index) {
    return horizontalSequences.get(index);
  }

  @Override
  public Ship putShipInSequence(Integer seqIndex, Integer initRandomPosition, Integer shipLength) {
    List<Integer> fieldsIndexesInSequence = IntStream
        .range(initRandomPosition, initRandomPosition + shipLength)
        .boxed()
        .collect(Collectors.toList());
    setBufferAround(seqIndex, new LinkedList<>(fieldsIndexesInSequence));
    return horizontalSequences.get(seqIndex).setShip(fieldsIndexesInSequence);
  }

  private void setBufferAround(Integer sequenceIndex, LinkedList<Integer> fieldsIndexesInSequence) {
    if (sequenceIndex > FIRST_SEQUENCE_INDEX) {
      horizontalSequences.get(sequenceIndex - SEQUENCE_STEP).setBuffered(fieldsIndexesInSequence);
    }
    int lastSequenceIndex = horizontalSequences.size() - SEQUENCE_STEP;
    if (sequenceIndex < lastSequenceIndex) {
      horizontalSequences.get(sequenceIndex + SEQUENCE_STEP).setBuffered(fieldsIndexesInSequence);
    }
    if (!BordersCheck.isOnLeftBorder(fieldsIndexesInSequence.getFirst())) {
      horizontalSequences
          .get(sequenceIndex)
          .setBuffered(fieldsIndexesInSequence.getFirst() - STEP_OF_FIELDS_IN_SEQUENCE);
    }
    if (!BordersCheck.isOnRightBorder(fieldsIndexesInSequence.getLast())) {
      horizontalSequences
          .get(sequenceIndex)
          .setBuffered(fieldsIndexesInSequence.getLast() + STEP_OF_FIELDS_IN_SEQUENCE);
    }
  }
}
