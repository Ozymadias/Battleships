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

//TODO: I'm lacking Javadocs! Please, add them master!
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

  //TODO: am I performant?
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

  //TODO: Tomek to send link over
  @Override
  public void accept(Messagable event) {
    if (event instanceof SalvoResult) {
      SalvoResult salvoResult = (SalvoResult) event;
      processSalvo(salvoResult.getSalvoPositions());
      if (salvoResult.getGameResult() != GameResult.NONE) {
        processGameResult(salvoResult.getGameResult());
      }
    }
  }

  private void processGameResult(GameResult gameResult) {
    dockedGridPane.setDisable(true);


    String resultInfo = BattleshipUtils.provideEmptyString();
    //TODO: consider this version instead (will have to change strings downstream (resultInfo)
    if (gameResult == GameResult.NONE) return;

    resultInfo = gameResult.toString();
    

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(resourceBundle.getString("INFORMATION_DIALOG"));
    alert.setHeaderText(resourceBundle.getString("GAME_RESULT_HEADER"));
    alert.setContentText(resourceBundle.getString(resultInfo));
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.showAndWait();
  }

  private void processSalvo(List<Integer> salvoPositions) {
    updateBoard(salvoPositions);
    SalvoCount salvoCount = countRemainUnbrokenMasts();
    DataBus.getInstance().publish(salvoCount);
  }

  private void updateBoard(List<Integer> salvoPositions) {
    for (Integer positionOfShot : salvoPositions) {
      board.getFields().get(positionOfShot).shoot();
      //TODO: what would be required to instead code:
	//       shot(positionOfShot);
      setUpPlayerBoardDocked();
    }
  }

  //TODO: count OF something, not count something, unless you want to ask somebody: hey, count something! 
  //TODO: idiom for getting unbroken ship fields from the board should be on the board I think? Unless you only use it here and don't plan on using it elsewhere
  private SalvoCount countRemainUnbrokenMasts() {
    long count = board.getFields().stream()
        .filter(field -> field.isUnbrokenShipOn())
        .count();
    return new SalvoCount((int) count);
  }
}
