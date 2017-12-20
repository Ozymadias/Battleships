package battleships.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {


    private static final int BOARD_SIZE = 100;

    List<Field> fields;

    Board(List<Field> fields) {
        this.fields = fields;
    }

    List<Field> getFields(){
        return this.fields;
    }

    BoardNode rectangleForPosition(int position){
        return BoardNode.build(fields.get(position));
    }

    void shootAtField(Integer position){
        fields.get(position).shoot();
    }

    public boolean isFieldEmpty(Integer fieldPosition) {
        return fields.get(fieldPosition).getState().equals(FieldState.EMPTY);
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