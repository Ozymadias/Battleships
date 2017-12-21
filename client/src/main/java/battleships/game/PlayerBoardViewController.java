package battleships.game;

import battleships.communication.ClientHandler;
import battleships.ships.Fleet;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class PlayerBoardViewController {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;

    @FXML
    private GridPane dockedGridPane;

    private Fleet fleet;

    private Board board;

    ClientHandler clientHandler;

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @FXML
    private void initialize(){
        ShipsRandomize shipsRandomize = ShipsRandomize.build(Board.build());
        fleet = shipsRandomize.placeAllFloat();
        Board board = shipsRandomize.getBoard();
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

    public Fleet getFleet(){
        return fleet;
    }
}
