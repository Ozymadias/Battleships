package battleships.game;

import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messageable;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class OpponentBoardViewController implements Member {

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

  private Board enemyBoard;

  @FXML
  private void initialize() {
    enemyBoard = Board.build();
    setUpBoardView();
    shootsLeftCountText.setText(shootsLeftCount.toString());
    DataBus.getInstance().subscribeMember(this);
  }

  private void setUpBoardView() {
    for (int row = 0; row < BOARD_ROW_COUNT; row++) {
      for (int col = 0; col < BOARD_COLUMN_COUNT; col++) {
        BoardNode boardNode = this.enemyBoard.rectangleForPosition(row * BOARD_COLUMN_COUNT + col);
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
  private void sendSalvoClick() {
    salvoBtn.setDisable(true);
    DataBus.getInstance().publishRequest(new Salvo(this.salvoList));
    salvoList.clear();
  }

  /**
   * Prevents player from shooting incomplete salvo. In order to shoot player needs to mark
   * number of shots on the board otherwise salvo button will be disabled.
   * @param count integer representation of shots left in turn.
   */
  public void setShootsLeftCount(Integer count) {
    this.shootsLeftCount = count;
    this.shootsLeftCountText.setText(count.toString());
    if (shootsLeftCount <= 0) {
      salvoBtn.setDisable(false);
    } else {
      salvoBtn.setDisable(true);
    }
  }

  private void processShot(Integer fieldPosition) {
    if (this.shootsLeftCount > 0) {
      enemyBoard.shootAtField(fieldPosition);
      setShootsLeftCount(this.shootsLeftCount - 1);
      setUpBoardView();
      salvoList.add(fieldPosition);
    }
  }

  @Override
  public void accept(Messageable data) {
    if (data instanceof SalvoCount) {
      salvoBtn.setDisable(false);
      processSalvoCount((SalvoCount) data);
      log.info("SalvoCount received by controller");
    } else if (data instanceof SalvoResult) {
      SalvoResult salvoResult = (SalvoResult) data;
      updateBoard((ArrayList<Integer>) salvoResult.getResultList());
      log.info("SalvoResult received by controller");
      if (salvoResult.getGameResult() != GameResult.NONE) {
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
    resultList
        .forEach(pos -> enemyBoard.getFields().get(pos).setBrokenShipPartOn());
    setUpBoardView();
  }

  private void processSalvoCount(SalvoCount event) {
    setShootsLeftCount(event.getCount());
  }
}
