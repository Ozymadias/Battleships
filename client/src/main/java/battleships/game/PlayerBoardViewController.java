package battleships.game;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class PlayerBoardViewController {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;

    @FXML
    private GridPane dockedGridPane;

    private Board board;

    @FXML
    private void initialize(){
        board = Board.build();
        ShipsRandomize shipsRandomize = ShipsRandomize.build(board);
        board = shipsRandomize.placeAllFloat();
        setUpPlayerBoardDocked();
    }

    private void setUpPlayerBoardDocked(){
        for(int row = 0; row < BOARD_ROW_COUNT; row++){
            for(int col = 0; col < BOARD_COLUMN_COUNT; col++){
                BoardNode boardNode = this.board.rectangleForPosition(row*BOARD_COLUMN_COUNT + col);
                GridPane.setRowIndex(boardNode.getStackPane(), row);
                GridPane.setColumnIndex(boardNode.getStackPane(), col);
                dockedGridPane.getChildren().addAll(boardNode.getStackPane());
            }
        }
    }
}
