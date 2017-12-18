package battleships.game;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class OpponentBoardViewController {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;

    @FXML
    BorderPane mainPane;

    @FXML
    GridPane dockedGridPane;

    private Board opponentBoard;

    @FXML
    private void initialize(){
        opponentBoard = Board.build();
        setUpBoardView();
    }

    private void setUpBoardView(){
        for(int row = 0; row < BOARD_ROW_COUNT; row++){
            for(int col = 0; col < BOARD_COLUMN_COUNT; col++){
                StackPane stackPane = this.opponentBoard.rectangleForPosition(row*BOARD_COLUMN_COUNT + col);
                GridPane.setRowIndex(stackPane, row);
                GridPane.setColumnIndex(stackPane, col);
                dockedGridPane.getChildren().addAll(stackPane);
            }
        }
    }

}
