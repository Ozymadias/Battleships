package battleships.init.sequence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {

    private static final int BOARD_FIELDS_COUNT = 100;

    private final List<Field> fields;

    private Board(List<Field> fields) {
        this.fields = fields;
    }

    static Board build(){
        List<Field> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(Field::new)
                .collect(Collectors.toList());
        return new Board(fields);
    }

    List<Field> getFields(){
        return this.fields;
    }

    boolean isFieldEmpty(Integer position){
        return fields.get(position).getState() == FieldState.EMPTY;
    }
}
