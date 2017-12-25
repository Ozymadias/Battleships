package battleships.game;

import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messagable;
import battleships.communication.messages.SalvoResult;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

import java.util.List;

public class PlayerBoardViewController implements Member{

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;
    private Board board;

    @FXML
    private GridPane dockedGridPane;

    @FXML
    private void initialize(){
        DataBus.getInstance().subscribeMember(this);
    }

    public void setUpPlayerBoardDocked() {
        for (int row = 0; row < BOARD_ROW_COUNT; row++) {
            for (int col = 0; col < BOARD_COLUMN_COUNT; col++) {
                BoardNode boardNode = this.board.rectangleForPosition(row * BOARD_COLUMN_COUNT + col);
                GridPane.setRowIndex(boardNode.getStackPane(), row);
                GridPane.setColumnIndex(boardNode.getStackPane(), col);
                dockedGridPane.getChildren().addAll(boardNode.getStackPane());
            }
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void accept(Messagable event) {
        if(event instanceof SalvoResult){
            processSalvo(((SalvoResult) event).getSalvoPositions());
        }
    }

    private void processSalvo(List<Integer> salvoPositions){
        updateBoard(salvoPositions);
        SalvoCount salvoCount = countRemainUnbrokenMasts();
        DataBus.getInstance().publish(salvoCount);
    }

    private void updateBoard(List<Integer> salvoPositions) {
        for(Integer positionOfShot : salvoPositions){
            board.getFields().get(positionOfShot).shoot();
            setUpPlayerBoardDocked();
        }
    }

    private SalvoCount countRemainUnbrokenMasts() {
       long count = board.getFields().stream()
                                     .filter(field -> field.isUnbrokenShipOn() == true)
                                     .count();
       return new SalvoCount((int) count);
    }
}
