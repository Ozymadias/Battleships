package battleships.game;

import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.*;

class ShipsRandomize {

    private static final int SEQUENCE_COUNT = 10;

    final private HorizontalSequenceSet horizontalSequences;
    final private Board board;

    public ShipsRandomize(HorizontalSequenceSet horizontalSequences, Board board) {
        this.horizontalSequences = horizontalSequences;
        this.board = board;
    }

    static ShipsRandomize build(Board board){
        HorizontalSequenceSet horizontalSequenceSet = HorizontalSequenceSet.build(board);
        return new ShipsRandomize(horizontalSequenceSet, board);
    }

    Fleet placeAllFloat(){
        List<Integer> shipsToPlace = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
        List<Ship> ships = new ArrayList<>();
        shipsToPlace.forEach( masts -> ships.add(placeShipHorizontally(masts)));
        return new Fleet(ships);
    }

    private Ship placeShipHorizontally(int length){
        Integer randomRow;
        do{
            randomRow = new Random().nextInt(SEQUENCE_COUNT);
        }while(!horizontalSequences.get(randomRow).canContainShip(length));

        return horizontalSequences.randomlyPlaceShip(randomRow, length);
    }

    public Board getBoard() {
        return board;
    }
}
