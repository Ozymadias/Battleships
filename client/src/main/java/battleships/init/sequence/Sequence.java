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

    String statesMarksToString(){
        return fields.stream()
                .map(Field::stateMarkToString)
                .collect(Collectors.joining());
    }

    public Boolean canContainShip(Integer shipLength){
        return statesMarksToString().matches("e{" + shipLength + ",}");
    }

    public Integer firstEmptyFor(Integer shipLength){
        return statesMarksToString().indexOf(StringUtils.repeat("e", shipLength));
    }

    public Integer lastEmptyStartingBy(Integer position){
        Integer last = position;

        while (last < fields.size()
                && fields.get(last).getState().equals(FieldState.EMPTY)){
            last++;
        }

        return last;
    }
}
