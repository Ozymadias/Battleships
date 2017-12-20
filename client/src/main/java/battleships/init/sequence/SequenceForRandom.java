package battleships.init.sequence;

import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

//TODO: too many responsibilities? maybe additional class e.g. SequenceValidator
public class SequenceForRandom {

    private final List<FieldForRandom> fields;

    SequenceForRandom(List<FieldForRandom> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return fields.stream()
                .map(FieldForRandom::toString)
                .collect(Collectors.joining());

    }

    String positionsToString(){
        return fields.stream()
                .map(FieldForRandom::positionToString)
                .collect(Collectors.joining());
    }

    public String statesMarksToString(){
        return fields.stream()
                .map(FieldForRandom::stateMarkToString)
                .collect(Collectors.joining());
    }

    public Boolean canContainShip(Integer shipLength){
        return statesMarksToString().contains(StringUtils.repeat("e", shipLength));
    }

    public Integer firstEmptyFor(Integer shipLength){
        //TODO: why not returning field position
        return statesMarksToString().indexOf(StringUtils.repeat("e", shipLength));
    }

    public Integer lastEmptyStartingBy(Integer position){
        Integer last = position;

        while (last < fields.size() - 1
                && fields.get(last).getState().equals(FieldStateForRandom.EMPTY)) {
            last++;
        }

        return last;
    }

    public void setBuffered(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setState(FieldStateForRandom.BUFFER);
        }
    }

    public void setBuffered(Integer fieldPosition){
        fields.get(fieldPosition).setState(FieldStateForRandom.BUFFER);
    }

    public boolean isOnBorder(Integer fieldPosition){
        return fields.get(fieldPosition).getState().equals(FieldStateForRandom.BORDER);
    }

    public void setOccupied(List<Integer> fieldsPositions){
        for(Integer position : fieldsPositions){
            fields.get(position).setState(FieldStateForRandom.OCCUPIED);
        }
    }
}
