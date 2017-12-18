package battleships.game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Board {

    List<Field> fields;

    private Map<FieldState, Color> colorMap;

    private Board(List<Field> fields, Map<FieldState, Color> colorMap) {
        this.fields = fields;
        this.colorMap = colorMap;
    }

    static Board build(){
        Map<FieldState, Color> map = new HashMap<>();
        map.put(FieldState.EMPTY, Color.WHITE);
        map.put(FieldState.UNBROKEN_SHIP_PART, Color.GREEN);
        map.put(FieldState.BROKEN_SHIP_PART, Color.YELLOW);
        map.put(FieldState.SUNK_SHIP_PART, Color.RED);
        List<Field> emptyFields = IntStream.range(0, 100).mapToObj(p -> new Field(p)).collect(Collectors.toList());
        return new Board(emptyFields, map);
    }

    /**
     * created for temporary use
     */
    void generateExample(){
        //empty list
        this.fields = IntStream.range(0, 100).mapToObj(p -> new Field(p)).collect(Collectors.toList());
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
        fields.get(75).setShipPartOn();
        fields.get(85).setShipPartOn();

        fields.get(78).setShipPartOn();
        fields.get(88).setShipPartOn();

        fields.get(46).setShipPartOn();
        fields.get(47).setShipPartOn();

        //add 1-mast ship x 4
        fields.get(40).setShipPartOn();
        fields.get(18).setShipPartOn();
        fields.get(52).setShipPartOn();
        fields.get(82).setShipPartOn();
    }

    StackPane rectangleForPosition(int position){
        Rectangle rec = new Rectangle();
        rec.setWidth(30);
        rec.setHeight(30);
        rec.setFill(colorMap.get(fields.get(position).getState()));
        rec.setStroke(Color.BLACK);
        StackPane stackPane = new StackPane();
        if(fields.get(position).isShoted()){
            Text text = new Text("x");
            stackPane.getChildren().addAll(rec, text);
        }else{
            stackPane.getChildren().addAll(rec);
        }
        return stackPane;
    }


}