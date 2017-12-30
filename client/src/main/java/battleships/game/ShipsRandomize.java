package battleships.game;

import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class ShipsRandomize {

  private static final int SEQUENCE_COUNT = 10;


  private final HorizontalSeqSet horizontalSequences;
  private final VerticalSeqSet verticalSeqSet;

  private final Board board;

  private ShipsRandomize(HorizontalSeqSet horizontal, VerticalSeqSet verticalSet, Board board) {
    this.horizontalSequences = horizontal;
    this.verticalSeqSet = verticalSet;
    this.board = board;
  }

  public static ShipsRandomize build(Board board) {
    HorizontalSeqSet horizontalSeqSet = HorizontalSeqSet.build(board);
    VerticalSeqSet verticalSeqSet = VerticalSeqSet.build(board);
    return new ShipsRandomize(horizontalSeqSet, verticalSeqSet, board);
  }

  public Fleet placeAllFleet() {
    List<Integer> shipsToPlace = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
    List<Ship> ships = new ArrayList<>();
    shipsToPlace.forEach(masts -> ships.add(placeShip(masts)));
    return new Fleet(ships);
  }

  private Ship placeShip(int length) {
    Integer randomForDirection = new Random().nextInt(100);
    if (randomForDirection % 2 == 0) {
      return placeShipHorizontally(length);
    } else {
      return placeShipVertically(length);
    }
  }

  private Ship placeShipVertically(int length) {
    Integer randomRow;
    do {
      randomRow = new Random().nextInt(SEQUENCE_COUNT);
    } while (!verticalSeqSet.get(randomRow).canContainShip(length));

    return verticalSeqSet.randomlyPlaceShip(randomRow, length);
  }

  private Ship placeShipHorizontally(int length) {
    Integer randomRow;
    do {
      randomRow = new Random().nextInt(SEQUENCE_COUNT);
    } while (!horizontalSequences.get(randomRow).canContainShip(length));

    return horizontalSequences.randomlyPlaceShip(randomRow, length);
  }

  public Board getBoard() {
    return board;
  }
}
