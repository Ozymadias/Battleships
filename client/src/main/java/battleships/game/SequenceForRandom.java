package battleships.game;

import battleships.ships.Ship;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SequenceForRandom {

    private final int STEP = 1;

    private final List<Field> fields;

    SequenceForRandom(List<Field> fields) {
        this.fields = fields;
    }

    String statesMarksToString(){
        return fields.stream()
                .map(Field::stateMarkToString)
                .collect(Collectors.joining());
    }

    Boolean canContainShip(Integer shipLength){
        return statesMarksToString().contains(StringUtils.repeat("e", shipLength));
    }

    Integer firstEmptyFor(Integer shipLength){
        return statesMarksToString().indexOf(StringUtils.repeat("e", shipLength));
    }

    Integer lastEmptyStartingBy(Integer position){
        Integer last = position;
        while (last < fields.size() - STEP
                && fields.get(last).isEmpty()){
            last++;
        }
        return last;
    }

    void setBuffered(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setBuffer();
        }

        LinkedList<Integer> fieldsPos = new LinkedList<>(fieldsPositions);
        if(!(BordersCheck.isOnLeftBorder(fieldsPos.getFirst()))){
            fields.get(fieldsPos.getFirst()-STEP).setBuffer();
        }

        if(!(BordersCheck.isOnRightBorder(fieldsPos.getLast()))){
            fields.get(fieldsPos.getLast()+STEP).setBuffer();
        }
    }

    void setBuffered(Integer fieldPosition){
        fields.get(fieldPosition).setBuffer();
    }

    Ship setShip(List<Integer> positionsInSequence){
        List<Integer> boardIndexes = new ArrayList<>();

        positionsInSequence.stream()
                .forEach(
                        position -> {
                            fields.get(position).setUnbrokenShipPartOn();
                            boardIndexes.add(fields.get(position).getPosition());
                        }
                );

        return Ship.viaList(boardIndexes);
    }
}
