package battleships.game;

import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * The class provides random placement for fleet on given board.
 * It places ship both vertically and horizontally.
 */
public class ShipsRandomize {

  private static final int SEQUENCE_COUNT = 10;


  private final HorizontalSeqSet horizontalSeqSet;
  private final VerticalSeqSet verticalSeqSet;

  private final Board board;

  private ShipsRandomize(HorizontalSeqSet horizontalSet, VerticalSeqSet verticalSet, Board board) {
    this.horizontalSeqSet = horizontalSet;
    this.verticalSeqSet = verticalSet;
    this.board = board;
  }

  /**
   * Accepts a board as a parameter and generates all possible sequences
   * for a given board.
   * @param board representation of a board that game will be played on.
   * @return new instance of ShipRandomize with a board and all sequences generated.
   */
  public static ShipsRandomize build(Board board) {
    HorizontalSeqSet horizontalSeqSet = HorizontalSeqSet.build(board);
    VerticalSeqSet verticalSeqSet = VerticalSeqSet.build(board);
    return new ShipsRandomize(horizontalSeqSet, verticalSeqSet, board);
  }

  /**
   * Randomly places all ships on the board, one 4 mast , two 3 masts, three 2 mast
   * and four 1 mast ships.
   *
   * @return fleet of ships on random positions.
   */
  public Fleet placeAllFleet() {
    List<Integer> shipsToPlace = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
    List<Ship> ships = new ArrayList<>();
    shipsToPlace.forEach(masts -> ships.add(placeShip(masts)));
    return new Fleet(ships);
  }

  /**
   * Places single ship in random position.
   *
   * @param length represents integer value of ship length.
   * @return ship with random placement.
   */
  private Ship placeShip(int length) {
    Integer randomForDirection = new Random().nextInt(100);
    if (randomForDirection % 2 == 0) {
      return placeShipInSequence(length, horizontalSeqSet);
    } else {
      return placeShipInSequence(length, verticalSeqSet);
    }
  }

  private Ship placeShipInSequence(int length, SeqSet seqSet ) {
    Integer randomRow;
    do {
      randomRow = new Random().nextInt(SEQUENCE_COUNT);
    } while (!seqSet.get(randomRow).canContainShip(length));
    return seqSet.randomlyPlaceShip(randomRow, length);
  }

  public Board getBoard() {
    return board;
  }
}
