package battleships.game;

import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messagable;
import battleships.communication.messages.SalvoResult;
import battleships.utils.BattleshipUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PlayerBoardViewController implements Member, Initializable {

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;
    private Board board;
    private ResourceBundle resourceBundle;

    @FXML
    private GridPane dockedGridPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
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
            SalvoResult salvoResult = (SalvoResult) event;
            processSalvo(salvoResult.getSalvoPositions());
            if(salvoResult.getGameResult() != GameResult.NONE){
                processGameResult(salvoResult.getGameResult());
            }
        }
    }

    private void processGameResult(GameResult gameResult) {
        dockedGridPane.setDisable(true);

        String resultInfo = BattleshipUtils.provideEmptyString();

        switch (gameResult){
            case WIN: resultInfo = "WIN_INFO"; break;
            case LOOSE: resultInfo = "LOOSE_INFO"; break;
            case DRAW: resultInfo = "DRAW_INFO"; break;
            case NONE: return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("INFORMATION_DIALOG"));
        alert.setHeaderText(resourceBundle.getString("GAME_RESULT_HEADER"));
        alert.setContentText(resourceBundle.getString(resultInfo));
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
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
                                     .filter(field -> field.isUnbrokenShipOn())
                                     .count();
       return new SalvoCount((int) count);
    }
}
