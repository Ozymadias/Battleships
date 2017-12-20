package battleships.game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

class BoardNode extends StackPane {

    private static final int RECTANGLE_SIZE = 30;
    private static final String MARK_FOR_SHOT = "x";

    private final Integer index;
    private final StackPane stackPane;

    private BoardNode(Integer index, StackPane stackPane) {
        this.index = index;
        this.stackPane = stackPane;
    }

    static BoardNode build(Field field){
        Rectangle rec = new Rectangle();
        rec.setWidth(RECTANGLE_SIZE);
        rec.setHeight(RECTANGLE_SIZE);
        rec.setFill(field.getColorBasedOnState());
        rec.setStroke(Color.BLACK);
        StackPane stackPane = new StackPane();
        if(field.isShot()){
            Text text = new Text(MARK_FOR_SHOT);
            stackPane.getChildren().addAll(rec, text);
        }else{
            stackPane.getChildren().addAll(rec);
        }
        return new BoardNode(field.getPosition(), stackPane);
    }

    Integer getIndex(){
        return index;
    }

    StackPane getStackPane() {
        return stackPane;
    }
}
