package battleships;

import battleships.communication.DataBus;
import battleships.game.Board;
import battleships.game.OpponentBoardViewController;
import battleships.game.PlayerBoardViewController;
import battleships.game.ShipsRandomize;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;


public class RootLayoutController implements Initializable {

  private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";
  private static final String OPPONENT_BOARD_VIEW_FXML = "/fxml/OpponentBoardViewLayout.fxml";

  private ResourceBundle resourceBundle;

  private final BattleshipLog log = BattleshipLog.provideLogger(RootLayoutController.class);

  @FXML
  BorderPane borderPane;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.resourceBundle = resources;
    try {
      addOpponentBoardView();
      Board playerBoard = preparePlayerData();
      addPlayerBoardView(playerBoard);
    } catch (IOException e) {
      log.error(e);
    }
  }

  private Board preparePlayerData() {
    ShipsRandomize shipsRandomize = ShipsRandomize.build(Board.build());
    Fleet fleet = shipsRandomize.placeAllFleet();
    Board board = shipsRandomize.getBoard();
    DataBus.getInstance().publish(fleet);
    try {
      addPlayerBoardView(board);
    } catch (IOException e) {
      log.error(e);
    }
    return board;
  }

  private void addPlayerBoardView(Board board) throws IOException {
    final FXMLLoader loader = new FXMLLoader();
    loader.setResources(resourceBundle);
    loader.setLocation(App.class.getResource(PLAYER_BOARD_VIEW_FXML));
    borderPane.setLeft(loader.<BorderPane>load());
    final PlayerBoardViewController controller = loader.getController();
    controller.setBoard(board);
    controller.setUpPlayerBoardDocked();
  }

  private void addOpponentBoardView() throws IOException {
    final FXMLLoader loader = new FXMLLoader();
    loader.setLocation(App.class.getResource(OPPONENT_BOARD_VIEW_FXML));
    loader.setResources(resourceBundle);
    borderPane.setRight(loader.<BorderPane>load());
    OpponentBoardViewController controller = loader.getController();
    controller.setShootsLeftCount(20);
  }
}
