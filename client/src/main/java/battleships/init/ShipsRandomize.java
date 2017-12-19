package battleships.init;

import battleships.init.sequence.Sequence;
import battleships.init.sequence.SequencesGenerator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ShipsRandomize {

    private static final int SEQUENCE_COUNT = 10;

    List<Sequence> horizontalSequences;

    public ShipsRandomize(List<Sequence> horizontalSequences) {
        this.horizontalSequences = horizontalSequences;
    }

    static ShipsRandomize build(){
        SequencesGenerator sequencesGenerator = SequencesGenerator.build();
        List<Sequence> horizontalSequences = IntStream.iterate(0, i -> i+10)
                .limit(SEQUENCE_COUNT)
                .mapToObj(i-> sequencesGenerator.createHorizontalSequence(i))
                .collect(Collectors.toList());
        return new ShipsRandomize(horizontalSequences);
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

        randomizeShipIntoSequence(randomRow, length);
    }

    void randomizeShipIntoSequence(Integer rowNumber, Integer shipLength) {
        Integer firstEmptyPosition = horizontalSequences.get(rowNumber).firstEmptyFor(shipLength);
        Integer lastEmptyPosition = horizontalSequences.get(rowNumber).lastEmptyStartingBy(firstEmptyPosition);
        Integer firstPositionRandomized = new Random().ints(firstEmptyPosition, lastEmptyPosition-shipLength+1).findFirst().getAsInt();
        putShipIntoSequence(firstPositionRandomized, shipLength, rowNumber);
    }


    //TODO extract interface which will we be implemented by classes: horizontal sequence and vertical sequence
    void putShipIntoSequence(Integer firstPositionRandomized, Integer shipLength, Integer rowNumber){
        List<Integer> positions = IntStream.range(firstPositionRandomized, firstPositionRandomized+shipLength).boxed().collect(Collectors.toList());
        setBufferAround(rowNumber, new LinkedList<>(positions));
        horizontalSequences.get(rowNumber).setOccupied(positions);
    }

    void setBufferAround(Integer rowNumber, LinkedList<Integer> position){
        if(rowNumber > 0){
            horizontalSequences.get(rowNumber-1).setBuffered(position);
        }
        if(rowNumber < 10){
            horizontalSequences.get(rowNumber+1).setBuffered(position);
        }
        if(!horizontalSequences.get(rowNumber).isOnBorder(position.getFirst())){
            horizontalSequences.get(rowNumber).setBuffered(position.getFirst()-1);
        }
        if(!horizontalSequences.get(rowNumber).isOnBorder(position.getLast())){
            horizontalSequences.get(rowNumber).setBuffered(position.getLast()+1);
        }
    }

    public String statesMarksToString(){
        return horizontalSequences.stream()
                .map(seq -> seq.statesMarksToString())
                .collect(Collectors.joining("\n"));
    }
}
