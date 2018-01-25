package battleships.game;

import battleships.AlertWithProgressIndicator;
import battleships.communication.DataBus;
import battleships.communication.Member;
import battleships.communication.Messageable;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.logger.BattleshipLog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller of enemy board view.
 */
public class OpponentBoardViewController implements Member, Initializable {

  private static final int BOARD_ROW_COUNT = 10;
  private static final int BOARD_COLUMN_COUNT = 10;

  private final BattleshipLog log = BattleshipLog.provideLogger(OpponentBoardViewController.class);
  private final List<Integer> salvoList = new ArrayList<>();
  @FXML
  private GridPane dockedGridPane;
  @FXML
  private Text shootsLeftCountText;
  @FXML
  private Button salvoBtn;
  private Integer shootsLeftCount = 0;
  private Board enemyBoard;
  private ResourceBundle resourceBundle;

  /**
   * Called to initialize a controller.
   * It's setting view of enemy board.
   *
   * @param location The location used to resolve relative paths for the root object,
   *                or null if the location is not known.
   * @param resources ResourceBundle delivering proper translation
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.resourceBundle = resources;
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
    AlertWithProgressIndicator alertWithProgressIndicator
            = AlertWithProgressIndicator.asInstance(Alert.AlertType.INFORMATION,
            "",
            resourceBundle.getString("SERVER_RESPONSE_WAITING"));
    alertWithProgressIndicator.initModality(Modality.APPLICATION_MODAL);
    alertWithProgressIndicator.initOwner(dockedGridPane.getScene().getWindow());
    DataBus.getInstance().publishRequest(new Salvo(this.salvoList), alertWithProgressIndicator);
    salvoList.clear();
  }

  /**
   * Prevents player from shooting incomplete salvo. In order to shoot player needs to mark
   * number of shots on the board otherwise salvo button will be disabled.
   *
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
