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

    static BoardBuilder withCleanFields(){
        List<Field> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(Field::new)
                .collect(Collectors.toList());
        return new BoardBuilder(fields);
    }

    static BoardBuilder withBorders(){
        List<Field> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(Field::new)
                .collect(Collectors.toList());
        //top border
        fields.stream()
                .filter(field -> field.getPosition() >= 0 && field.getPosition() <= 9)
                .forEach(field -> field.setTopBorder());
        //bottom border
        fields.stream()
                .filter(field -> field.getPosition() >= 90 && field.getPosition() <= 99)
                .forEach(field -> field.setBottomBorder());
        //left & right border
        fields.stream()
                .filter(field -> isOnLeftBorder(field.getPosition()))
                .forEach(field -> field.setLeftBorder());
        fields.stream()
                .filter(field -> isOnRightBorder(field.getPosition()))
                .forEach(field -> field.setRightBorder());
        return new BoardBuilder(fields);
    }

    Board build(){
       return new Board(fields);
    }

    static Boolean isOnLeftBorder(Integer number){
        return new Integer(number % 10).equals(0);
    }

    static Boolean isOnRightBorder(Integer number){
        return new Integer(number % 10).equals(9);
    }


}
