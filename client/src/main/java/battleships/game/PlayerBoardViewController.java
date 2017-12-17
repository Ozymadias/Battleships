package battleships.game;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Optional;
import java.util.Random;

public class PlayerBoardViewController {

    @FXML
    BorderPane mainPane;

    @FXML
    GridPane dockedGridPane;

    private Optional<Board> board = Optional.empty();

    public void setBoard(Board board) {
        this.board = Optional.of(board);
    }

    /*
     * automatically called after fxml file has been loaded
     */
    @FXML
    private void initialize(){
        if(!board.isPresent()){
            board = Optional.of(Board.buildExample());
        }
        setUpPlayerBoardDocked();
    }

    private void setUpPlayerBoardDocked(){
        Random rand = new Random();
        Color[] colors = {Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED};

        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                Rectangle rec = new Rectangle();
                int n = rand.nextInt(4);
                rec.setWidth(30);
                rec.setHeight(30);
                rec.setFill(colors[n]);
                rec.setStroke(Color.BLACK);

                //adds text to rectangle
                Text text = new Text("x");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(rec, text);

                GridPane.setRowIndex(stackPane, row);
                GridPane.setColumnIndex(stackPane, col);
                dockedGridPane.getChildren().addAll(stackPane);

//                GridPane.setRowIndex(rec, row);
//                GridPane.setColumnIndex(rec, col);
//                dockedGridPane.getChildren().addAll(rec);
            }
        }
    }
}
