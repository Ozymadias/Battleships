package battleships.init.sequence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {

    private final List<Field> fields;

    Board(List<Field> fields) {
        this.fields = fields;
    }

    List<Field> getFields(){
        return this.fields;
    }

    boolean isFieldEmpty(Integer position){
        return fields.get(position).getState() == FieldState.EMPTY;
    }

    String statesMarksToString(){
        StringBuilder builder = new StringBuilder();
        for(Field field : fields){
            if(field.getPosition() % 10 == 0){
                builder.append("\n");
            }
            builder.append(field.stateMarkToString());
        }
        return builder.toString();
    }
}
