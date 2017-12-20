package battleships.game;

import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//TODO: too many responsibilities? maybe additional class e.g. SequenceValidator
public class SequenceForRandom {

    private final List<Field> fields;

    SequenceForRandom(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return fields.stream()
                .map(Field::toString)
                .collect(Collectors.joining());

    }

    String positionsToString(){
        return fields.stream()
                .map(Field::positionToString)
                .collect(Collectors.joining());
    }

    public String statesMarksToString(){
        return fields.stream()
                .map(Field::stateMarkToString)
                .collect(Collectors.joining());
    }

    public Boolean canContainShip(Integer shipLength){
        return statesMarksToString().contains(StringUtils.repeat("e", shipLength));
    }

    public Integer firstEmptyFor(Integer shipLength){
        return statesMarksToString().indexOf(StringUtils.repeat("e", shipLength));
    }

    public Integer lastEmptyStartingBy(Integer position){
        Integer last = position;

        while (last < fields.size() - 1
                && fields.get(last).getState().equals(FieldState.EMPTY)) {
            last++;
        }

        return last;
    }

    public void setBuffered(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setState(FieldState.BUFFER);
        }

        if(!isOnBorder(fieldsPositions.get(0))){
            fields.get(fieldsPositions.get(0)-1).setState(FieldState.BUFFER);
        }

        if(!isOnBorder(fieldsPositions.get(fieldsPositions.size()-1))){
            fields.get(fieldsPositions.get(fieldsPositions.size()-1)+1).setState(FieldState.BUFFER);
        }
    }

    public void setBuffered(Integer fieldPosition){
        fields.get(fieldPosition).setState(FieldState.BUFFER);
    }

    public boolean isOnBorder(Integer fieldPosition){
        return fields.get(fieldPosition).getState().equals(FieldState.BORDER);
    }

    public boolean isOnBuffer(Integer fieldPosition){
        return fields.get(fieldPosition).getState().equals(FieldState.BUFFER);
    }

    public void setShip(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setState(FieldState.UNBROKEN_SHIP_PART);
        }
    }
}
