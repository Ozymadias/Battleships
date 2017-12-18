package battleships.game;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {


    private static final int BOARD_SIZE = 100;

    List<Field> fields;

    private Board(List<Field> fields) {
        this.fields = fields;
    }

    static Board build(){
        List<Field> emptyFields = IntStream.range(0, BOARD_SIZE).mapToObj(p -> new Field(p)).collect(Collectors.toList());
        return new Board(emptyFields);
    }

    /**
     * created for temporary use
     */
    void generateExample(){
        //empty list
        this.fields = IntStream.range(0, BOARD_SIZE).mapToObj(p -> new Field(p)).collect(Collectors.toList());
        //add 4-mast ship
        fields.stream()
                .filter(p -> (p.getPosition() > 2) && (p.getPosition() < 7))
                .forEach(p -> p.setShipPartOn());
        //add 3-mast ship x 2
        fields.stream()
                .filter(p -> (p.getPosition().equals(70)) || (p.getPosition().equals(80)) || (p.getPosition().equals(90)))
                .forEach(p -> p.setShipPartOn());
        fields.stream()
                .filter(p -> (p.getPosition() > 20) && (p.getPosition() < 24))
                .forEach(p -> p.setShipPartOn());
        //add 2-mast ship x 3
        fields.get(75).setShipPartOn(); fields.get(85).setShipPartOn();
        fields.get(78).setShipPartOn(); fields.get(88).setShipPartOn();
        fields.get(46).setShipPartOn(); fields.get(47).setShipPartOn();
        //add 1-mast ship x 4
        fields.get(40).setShipPartOn();
        fields.get(18).setShipPartOn();
        fields.get(52).setShipPartOn();
        fields.get(82).setShipPartOn();
    }

    BoardNode rectangleForPosition(int position){
        return BoardNode.build(fields.get(position));
    }

    void shootAtField(Integer position){
        fields.get(position).shoot();
    }


}