package battleships.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private static final int BOARD_FIELDS_COUNT = 100;

    private final List<Field> fields;

    private Board(List<Field> fields) {
        this.fields = fields;
    }

    public static Board build(){
        List<Field> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(Field::new)
                .collect(Collectors.toList());
        return new Board(fields);
    }

    List<Field> getFields(){
        return this.fields;
    }

    final BoardNode rectangleForPosition(int position){
        return BoardNode.build(fields.get(position));
    }

    void shootAtField(Integer position){
        fields.get(position).shoot();
    }
}