package battleships.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {

    List<Field> fields;

    Board(List<Field> fields) {
        this.fields = fields;
    }

    /**
     * this is static factory method created for temporary use
     */
    static Board buildExample(){
        //empty list
        List<Field> fields = IntStream.range(0, 100).mapToObj(p -> new Field(p)).collect(Collectors.toList());
        //add 4-mast ship
        fields.stream()
                .filter(p -> (p.getPosition() > 2) && (p.getPosition() < 7))
                .forEach(p -> p.setShipPartOn());
        //add 3-mast ship x 2
        fields.stream()
                .filter(p -> (p.getPosition().equals(70)) && (p.getPosition().equals(80)) && (p.getPosition().equals(90)))
                .forEach(p -> p.setShipPartOn());
        fields.stream()
                .filter(p -> (p.getPosition() > 20) && (p.getPosition() < 24))
                .forEach(p -> p.setShipPartOn());
        //add 2-mast ship x 3
        fields.stream()
                .filter(p -> (p.getPosition().equals(75)) && (p.getPosition().equals(85)))
                .forEach(p-> p.setShipPartOn());
        fields.stream()
                .filter(p -> (p.getPosition().equals(78)) && (p.getPosition().equals(88)))
                .forEach(p-> p.setShipPartOn());
        fields.stream()
                .filter(p -> (p.getPosition().equals(46)) && (p.getPosition().equals(85)))
                .forEach(p-> p.setShipPartOn());
        //add 1-mast ship x 4
        fields.get(40).setShipPartOn();
        fields.get(18).setShipPartOn();
        fields.get(52).setShipPartOn();
        fields.get(82).setShipPartOn();

        return new Board(fields);
    }
}