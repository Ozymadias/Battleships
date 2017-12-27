package battleships.game;

import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messagable;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class OpponentBoardViewController implements Member{

    private static final int BOARD_ROW_COUNT = 10;
    private static final int BOARD_COLUMN_COUNT = 10;

    private final BattleshipLog log = BattleshipLog.provideLogger(OpponentBoardViewController.class);

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
        DataBus.getInstance().subscribeMember(this);
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

    @FXML
    private void sendSalvoClick(){
        salvoBtn.setDisable(true);
        DataBus.getInstance().sendRequest(new Salvo(this.salvoList));
        salvoList.clear();
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

    @Override
    public void accept(Messagable event) {
        if(event instanceof SalvoCount){
            salvoBtn.setDisable(false);
            processSalvoCount((SalvoCount) event);
            log.info("SalvoCount received by controller");
        }else if(event instanceof SalvoResult){
            SalvoResult salvoResult = (SalvoResult) event;
            updateBoard((ArrayList<Integer>) salvoResult.getResultList());
            log.info("SalvoResult received by controller");
            if(salvoResult.getGameResult() != GameResult.NONE){
                gameEnd();
            }
        }
    }

    private void gameEnd() {
        salvoBtn.setDisable(true);
        this.shootsLeftCount = 0;
        dockedGridPane.setDisable(true);
    }

    private void updateBoard(ArrayList<Integer> resultList) {
        resultList.stream()
                .forEach(pos -> opponentBoard.getFields().get(pos).setBrokenShipPartOn());
        setUpBoardView();
    }

    private void processSalvoCount(SalvoCount event) {
        setShootsLeftCount(event.getCount());
    }
}
