package battleships.init.sequence;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Sequence {

    private final List<Field> fields;

    Sequence(List<Field> fields) {
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

        try {
            while (last < fields.size() - 1
                    && fields.get(last).getState().equals(FieldState.EMPTY)) {
                last++;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("aaaaa!");
        }

        return last;
    }

    public void setBuffered(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setState(FieldState.BUFFER);
        }
    }

    public void setBuffered(Integer fieldPosition){
        fields.get(fieldPosition).setState(FieldState.BUFFER);
    }

    public boolean isOnBorder(Integer fieldPosition){
        return fields.get(fieldPosition).getState().equals(FieldState.BORDER);
    }

    public void setOccupied(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setState(FieldState.OCCUPIED);
        }
    }
}
