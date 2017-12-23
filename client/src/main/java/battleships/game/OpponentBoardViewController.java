package battleships.game;

import battleships.RootLayoutController;
import battleships.communication.messages.Salvo;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpponentBoardViewController {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;
    private RootLayoutController rootLayoutController;

    @FXML
    private GridPane dockedGridPane;

    @FXML
    private Text shootsLeftCountText;

    @FXML
    private Button salvoBtn;

    private Integer shootsLeftCount = 0;

    private final List<Integer> salvoList = new ArrayList<>();

    private Board opponentBoard;

    @FXML
    private void initialize(){
        opponentBoard = Board.build();
        setUpBoardView();
        shootsLeftCountText.setText(shootsLeftCount.toString());
    }

    private void setUpBoardView() {
        for (int row = 0; row < BOARD_ROW_COUNT; row++) {
            for (int col = 0; col < BOARD_COLUMN_COUNT; col++) {
                BoardNode boardNode = this.opponentBoard.rectangleForPosition(row * BOARD_COLUMN_COUNT + col);
                boardNode.getStackPane().addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    int shotPosition = boardNode.getIndex();
                    processShot(shotPosition);
                });
                GridPane.setRowIndex(boardNode.getStackPane(), row);
                GridPane.setColumnIndex(boardNode.getStackPane(), col);
                dockedGridPane.getChildren().addAll(boardNode.getStackPane());
            }
        }
    }

    public void sendSalvoClick(ActionEvent actionEvent) {
        rootLayoutController.process(new Salvo(this.salvoList));
        salvoList.clear();
        salvoBtn.setDisable(true);
    }

    public void setRootLayoutController(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    public void setShootsLeftCount(Integer count){
        this.shootsLeftCount = count;
        this.shootsLeftCountText.setText(count.toString());
        if(shootsLeftCount <= 0){
            salvoBtn.setDisable(false);
        }else{
            salvoBtn.setDisable(true);
        }
    }

    private void processShot(Integer fieldPosition){
        if(this.shootsLeftCount > 0){
            opponentBoard.shootAtField(fieldPosition);
            setShootsLeftCount(this.shootsLeftCount-1);
            setUpBoardView();
            salvoList.add(fieldPosition);
        }
    }

}
