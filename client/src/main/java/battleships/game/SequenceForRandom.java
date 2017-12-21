package battleships.game;

import battleships.ships.Ship;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SequenceForRandom {

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
        while (last < fields.size() - 1
                && fields.get(last).isEmpty()){
            last++;
        }
        return last;
    }

    void setBuffered(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setBuffer();
        }

        if(!(BordersCheck.isOnLeftBorder(fieldsPositions.get(0)))){
            fields.get(fieldsPositions.get(0)-1).setBuffer();
        }

        if(!(BordersCheck.isOnRightBorder(fieldsPositions.get(fieldsPositions.size()-1)))){
            fields.get(fieldsPositions.get(fieldsPositions.size()-1)+1).setBuffer();
        }
    }

    void setBuffered(Integer fieldPosition){
        fields.get(fieldPosition).setBuffer();
    }

    Ship setShip(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setShipPartOn();
        }
        return Ship.viaList(fieldsPositions);
    }
}
