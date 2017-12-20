package battleships.init.sequence;

import java.util.List;

public class BoardForRandom {

    private final List<FieldForRandom> fields;

    BoardForRandom(List<FieldForRandom> fields) {
        this.fields = fields;
    }

    List<FieldForRandom> getFields(){
        return this.fields;
    }

    boolean isFieldEmpty(Integer position){
        return fields.get(position).getState() == FieldStateForRandom.EMPTY;
    }

    String statesMarksToString(){
        StringBuilder builder = new StringBuilder();
        for(FieldForRandom field : fields){
            if(field.getPosition() % 10 == 0){
                builder.append("\n");
            }
            builder.append(field.stateMarkToString());
        }
        return builder.toString();
    }
}
