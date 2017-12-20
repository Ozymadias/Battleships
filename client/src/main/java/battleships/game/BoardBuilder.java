package battleships.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardBuilder {

    private static final int BOARD_FIELDS_COUNT = 100;

    private final List<Field> fields;

    public BoardBuilder(List<Field> fields) {
        this.fields = fields;
    }

    static Board build(){
        List<Field> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(Field::new)
                .collect(Collectors.toList());
        return new Board(fields);
    }

}
