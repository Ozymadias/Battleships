package battleships.game;

import static battleships.game.SeqCount.DEFAULT;

import battleships.ships.Ship;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class VerticalSeqSet implements SeqSet {

  private final List<SeqForRandom> verticalSequences;

  private static final int STEP_OF_FIELDS_POSITIONS_IN_SEQUENCE = 10;
  private static final int SEQUENCE_STEP = 1;
  private static final int SEQUENCE_LENGTH = 10;
  private static final int FIRST_SEQUENCE_INDEX = 0;
  private static final int INCREMENTATION = 1;

  private VerticalSeqSet(List<SeqForRandom> verticalSequences) {
    this.verticalSequences = verticalSequences;
  }

  /**
   * Generate list of all possible vertical sequences for given board.
   *
   * @param board board that game will be played on.
   * @return list of all possible vertical sequences.
   */
  public static VerticalSeqSet build(Board board) {
    List<SeqForRandom> sequenceList = IntStream.iterate(0, i -> i + SEQUENCE_STEP)
        .limit(DEFAULT.getValue())
        .mapToObj(i -> createSingleSequence(i, board))
        .collect(Collectors.toList());
    return new VerticalSeqSet(sequenceList);
  }

  private static SeqForRandom createSingleSequence(int first, Board board) {
    List<Integer> numbersInSequence = IntStream
        .iterate(first, i -> i + STEP_OF_FIELDS_POSITIONS_IN_SEQUENCE)
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
    return verticalSequences.get(index);
  }

  @Override
  public Ship putShipInSequence(Integer seqIndex, Integer initRandPosition, Integer shipLength) {
    List<Integer> fieldsIndexesInSequence
        = IntStream.range(initRandPosition, initRandPosition + shipLength)
        .boxed()
        .collect(Collectors.toList());
    setBufferAround(seqIndex, new LinkedList<>(fieldsIndexesInSequence));
    return verticalSequences.get(seqIndex).setShip(fieldsIndexesInSequence);
  }

  private void setBufferAround(Integer seqIndex, LinkedList<Integer> fieldsIndexesInSeq) {
    if (seqIndex > FIRST_SEQUENCE_INDEX) {
      verticalSequences.get(seqIndex - SEQUENCE_STEP).setBuffered(fieldsIndexesInSeq);
    }
    int lastSequenceIndex = verticalSequences.size() - SEQUENCE_STEP;
    if (seqIndex < lastSequenceIndex) {
      verticalSequences.get(seqIndex + SEQUENCE_STEP).setBuffered(fieldsIndexesInSeq);
    }

    if (!BordersCheck.isOnLeftBorder(fieldsIndexesInSeq.getFirst())) {
      verticalSequences.get(seqIndex).setBuffered(fieldsIndexesInSeq.getFirst() - INCREMENTATION);
    }
    if (!BordersCheck.isOnRightBorder(fieldsIndexesInSeq.getLast())) {
      verticalSequences.get(seqIndex).setBuffered(fieldsIndexesInSeq.getLast() + INCREMENTATION);
    }
  }
}
