package battleships.game;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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
        opponentBoard = BoardBuilder.withCleanFields().build();
        setUpBoardView();
    }

    private void setUpBoardView() {
        for (int row = 0; row < BOARD_ROW_COUNT; row++) {
            for (int col = 0; col < BOARD_COLUMN_COUNT; col++) {
                BoardNode boardNode = this.opponentBoard.rectangleForPosition(row * BOARD_COLUMN_COUNT + col);
                boardNode.getStackPane().addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    opponentBoard.shootAtField(boardNode.getPosition());
                    setUpBoardView();
                });
                GridPane.setRowIndex(boardNode.getStackPane(), row);
                GridPane.setColumnIndex(boardNode.getStackPane(), col);
                dockedGridPane.getChildren().addAll(boardNode.getStackPane());
            }
        }
    }

}
