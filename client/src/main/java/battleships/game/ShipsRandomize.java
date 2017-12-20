package battleships.game;

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

    Board placeAllFloat(){
        List<Integer> ships = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
        ships.forEach( masts -> placeShipHorizontally(masts));
        return board;
    }

    private void placeShipHorizontally(int length){
        Integer randomRow;
        do{
            randomRow = new Random().nextInt(SEQUENCE_COUNT);
        }while(!horizontalSequences.get(randomRow).canContainShip(length));

        horizontalSequences.randomlyPlaceShip(randomRow, length);
    }
}
