package battleships.init.sequence;

import java.util.*;

class ShipsRandomize {

    private static final int SEQUENCE_COUNT = 10;

    HorizontalSequenceSet horizontalSequences;

    public ShipsRandomize(HorizontalSequenceSet horizontalSequences) {
        this.horizontalSequences = horizontalSequences;
    }

    static ShipsRandomize build(BoardForRandom board){
        HorizontalSequenceSet horizontalSequenceSet = HorizontalSequenceSet.build(board);
        return new ShipsRandomize(horizontalSequenceSet);
    }

    void placeAllFloat(){
        placeShipHorizontally(4);
        placeShipHorizontally(3);
        placeShipHorizontally(3);
        placeShipHorizontally(2);
        placeShipHorizontally(2);
        placeShipHorizontally(2);
        placeShipHorizontally(2);
        placeShipHorizontally(1);
        placeShipHorizontally(1);
        placeShipHorizontally(1);
        placeShipHorizontally(1);
    }

    private void placeShipHorizontally(int length){
        Integer randomRow = 0;
        do{
            randomRow = new Random().nextInt(10);
        }while(!horizontalSequences.get(randomRow).canContainShip(length)); //sprawdzenie czy w danym wierszu są wogóle wolne miejsca

        horizontalSequences.randomlyPlaceShip(randomRow, length);
    }

    public String statesMarksToString(){
        return horizontalSequences.statesMarksToString();
    }
}
