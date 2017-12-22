package battleships.game;

import battleships.RootLayoutController;
import battleships.communication.messages.Salvo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.Arrays;

public class OpponentBoardViewController {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;
    private RootLayoutController rootLayoutController;

    @FXML
    private GridPane dockedGridPane;

    private Board opponentBoard;

    @FXML
    private void initialize(){
        opponentBoard = Board.build();
        setUpBoardView();
    }

    private void setUpBoardView() {
        for (int row = 0; row < BOARD_ROW_COUNT; row++) {
            for (int col = 0; col < BOARD_COLUMN_COUNT; col++) {
                BoardNode boardNode = this.opponentBoard.rectangleForPosition(row * BOARD_COLUMN_COUNT + col);
                boardNode.getStackPane().addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    opponentBoard.shootAtField(boardNode.getIndex());
                    setUpBoardView();
                });
                GridPane.setRowIndex(boardNode.getStackPane(), row);
                GridPane.setColumnIndex(boardNode.getStackPane(), col);
                dockedGridPane.getChildren().addAll(boardNode.getStackPane());
            }
        }
    }

    public void sendSalvoClick(ActionEvent actionEvent) {
        Salvo salvo = new Salvo(Arrays.asList(1, 2, 3, 4, 5, 6));
        rootLayoutController.process(salvo);
    }

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }
}
