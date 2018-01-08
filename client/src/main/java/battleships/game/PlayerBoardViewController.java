package battleships.game;

import battleships.communication.databus.DataBus;
import battleships.communication.databus.DataTypeVisitor;
import battleships.communication.databus.data.FleetAdapter;
import battleships.communication.databus.data.SalvoAdapter;
import battleships.communication.databus.data.SalvoCountAdapter;
import battleships.communication.databus.data.SalvoResultAdapter;
import battleships.communication.messages.SalvoResult;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

/**
 * Controller of player board.
 */
public class PlayerBoardViewController implements DataTypeVisitor, Initializable {

  private static final int BOARD_ROW_COUNT = 10;
  private static final int BOARD_COLUMN_COUNT = 10;
  private Board board;
  private ResourceBundle resourceBundle;

  @FXML
  private GridPane dockedGridPane;

  /**
   * This method is responsible for deliver ResourceBundle which is needed for proper translation.
   * It's also initialize PlayerBoardView.
   * @param location
   * @param resources ResourceBundle delivering proper translation
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.resourceBundle = resources;
    DataBus.getInstance().subscribeMember(this);
  }

  /**
   * Assign board representation to javaFx representation of a board.
   */
  public void setUpPlayerBoard() {
    for (int row = 0; row < BOARD_ROW_COUNT; row++) {
      for (int col = 0; col < BOARD_COLUMN_COUNT; col++) {
        BoardNode boardNode = this.board.rectangleForPosition(row * BOARD_COLUMN_COUNT + col);
        GridPane.setRowIndex(boardNode.getStackPane(), row);
        GridPane.setColumnIndex(boardNode.getStackPane(), col);
        dockedGridPane.getChildren().addAll(boardNode.getStackPane());
      }
    }
  }

  /**
   * Set board which is used to create javaFx representation of a board.
   * @param board
   */
  public void setBoard(Board board) {
    this.board = board;
  }

  private void processGameResult(GameResult gameResult) {
    dockedGridPane.setDisable(true);

    if(gameResult == GameResult.NONE) return;

    String resultInfo = gameResult.toString();

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(resourceBundle.getString("INFORMATION_DIALOG"));
    alert.setHeaderText(resourceBundle.getString("GAME_RESULT_HEADER"));
    alert.setContentText(resourceBundle.getString(resultInfo));
    alert.initModality(Modality.APPLICATION_MODAL);
    alert.showAndWait();
  }

  private void processSalvo(List<Integer> salvoPositions) {
    updateBoard(salvoPositions);
    SalvoCount salvoCount = new SalvoCount(board.unbrokenMastCount());
    SalvoCountAdapter salvoCountAdapter = new SalvoCountAdapter();
    salvoCountAdapter.setSalvoCount(salvoCount);
    DataBus.getInstance().publish(salvoCountAdapter);
  }

  private void updateBoard(List<Integer> salvoPositions) {
    for (Integer positionOfShot : salvoPositions) {
      board.getFields().get(positionOfShot).shoot();
      setUpPlayerBoard();
    }
  }

  @Override
  public void visit(SalvoAdapter salvoAdapter) {
    //do nothing
  }

  @Override
  public void visit(SalvoCountAdapter salvoCountAdapter) {
    //do nothing
  }

  @Override
  public void visit(SalvoResultAdapter salvoResultAdapter) {
      SalvoResult salvoResult = salvoResultAdapter.getSalvoResult();
      processSalvo(salvoResult.getSalvoPositions());
      if (salvoResult.getGameResult() != GameResult.NONE) {
        processGameResult(salvoResult.getGameResult());
      }
  }

  @Override
  public void visit(FleetAdapter fleetAdapter) {
    //do nothing
  }
}
