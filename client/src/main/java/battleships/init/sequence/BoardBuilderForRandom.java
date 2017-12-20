package battleships.init.sequence;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardBuilderForRandom {

    private static final int BOARD_FIELDS_COUNT = 100;

    private final List<FieldForRandom> fields;

    public BoardBuilderForRandom(List<FieldForRandom> fields) {
        this.fields = fields;
    }

    static BoardBuilderForRandom withCleanFields(){
        List<FieldForRandom> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(FieldForRandom::new)
                .collect(Collectors.toList());
        return new BoardBuilderForRandom(fields);
    }

    static BoardBuilderForRandom withBorders(){
        List<FieldForRandom> fields = IntStream.range(0,BOARD_FIELDS_COUNT)
                .mapToObj(FieldForRandom::new)
                .collect(Collectors.toList());
        //top border
        fields.stream()
                .filter(field -> field.getPosition() >= 0 && field.getPosition() <= 9)
                .forEach(field -> field.setState(FieldStateForRandom.BORDER));
//        //bottom border
        fields.stream()
                .filter(field -> field.getPosition() >= 90 && field.getPosition() <= 99)
                .forEach(field -> field.setState(FieldStateForRandom.BORDER));
        //left & right border
        fields.stream()
                .filter(field -> isOnLeftBorder(field.getPosition()) || isOnRightBorder(field.getPosition()))
                .forEach(field -> field.setState(FieldStateForRandom.BORDER));
        return new BoardBuilderForRandom(fields);
    }

    BoardForRandom build(){
       return new BoardForRandom(fields);
    }

    static Boolean isOnLeftBorder(Integer number){
        return new Integer(number % 10).equals(0);
    }

    static Boolean isOnRightBorder(Integer number){
        return new Integer(number % 10).equals(9);
    }


}
