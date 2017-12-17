package battleships.init.sequence;

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

    public String positionsToString(){
        return fields.stream()
                .map(Field::positionToString)
                .collect(Collectors.joining());
    }
}
