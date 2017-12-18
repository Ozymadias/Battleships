package battleships.game;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.Optional;

public class PlayerBoardViewController {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;

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
            board = Optional.of(Board.build());
            board.get().generateExample();
        }
        setUpPlayerBoardDocked();
    }

    private void setUpPlayerBoardDocked(){
        for(int row = 0; row < BOARD_ROW_COUNT; row++){
            for(int col = 0; col < BOARD_COLUMN_COUNT; col++){
                StackPane stackPane = board.get().rectangleForPosition(row*BOARD_COLUMN_COUNT + col);
                GridPane.setRowIndex(stackPane, row);
                GridPane.setColumnIndex(stackPane, col);
                dockedGridPane.getChildren().addAll(stackPane);
            }
        }
    }
}
