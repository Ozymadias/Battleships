package battleships.game;

import java.util.*;

class ShipsRandomize {

    private static final int SEQUENCE_COUNT = 10;

    HorizontalSequenceSet horizontalSequences;
    Board board;

    public ShipsRandomize(HorizontalSequenceSet horizontalSequences, Board board) {
        this.horizontalSequences = horizontalSequences;
        this.board = board;
    }

    static ShipsRandomize build(Board board){
        HorizontalSequenceSet horizontalSequenceSet = HorizontalSequenceSet.build(board);
        return new ShipsRandomize(horizontalSequenceSet, board);
    }

    void placeAllFloat(){
        placeShipHorizontally(4);
        placeShipHorizontally(3);
        placeShipHorizontally(3);
        placeShipHorizontally(2);
        placeShipHorizontally(2);
        placeShipHorizontally(2);
        placeShipHorizontally(2);
        //todo sometimes there are problems with executing random placement of this
//        placeShipHorizontally(1);
//        placeShipHorizontally(1);
//        placeShipHorizontally(1);
//        placeShipHorizontally(1);
    }

    private void placeShipHorizontally(int length){
        Integer randomRow = 0;
        do{
            randomRow = new Random().nextInt(SEQUENCE_COUNT);
        }while(!horizontalSequences.get(randomRow).canContainShip(length)); //sprawdzenie czy w danym wierszu są wogóle wolne miejsca

        horizontalSequences.randomlyPlaceShip(randomRow, length);
    }

    public String statesMarksToString(){
        return horizontalSequences.statesMarksToString();
    }
}
