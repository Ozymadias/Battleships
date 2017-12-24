package battleships;

import battleships.communication.DataBus;
import battleships.communication.ServerComm;
import battleships.game.Board;
import battleships.game.OpponentBoardViewController;
import battleships.game.PlayerBoardViewController;
import battleships.game.ShipsRandomize;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayoutController {

    private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";
    private static final String OPPONENT_BOARD_VIEW_FXML = "/fxml/OpponentBoardViewLayout.fxml";

    BattleshipLog log = BattleshipLog.provideLogger(RootLayoutController.class);

    private Fleet fleet;

    @FXML
    BorderPane borderPane;

    @FXML
    private void initialize(){
        try{
            addOpponentBoardView();
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void init(){
        ShipsRandomize shipsRandomize = ShipsRandomize.build(Board.build());
        this.fleet = shipsRandomize.placeAllFleet();
        Board board = shipsRandomize.getBoard();
        DataBus.getInstance().publish(this.fleet);
        try {
            addPlayerBoardView(board);
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void addPlayerBoardView(Board board) throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(PLAYER_BOARD_VIEW_FXML));
        borderPane.setLeft(loader.<BorderPane>load());
        final PlayerBoardViewController controller = loader.getController();
        controller.setBoard(board);
        controller.setUpPlayerBoardDocked();
    }

    private void addOpponentBoardView() throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(OPPONENT_BOARD_VIEW_FXML));
        borderPane.setRight(loader.<BorderPane>load());
        OpponentBoardViewController controller = loader.getController();
        controller.setRootLayoutController(this);
        controller.setShootsLeftCount(20);
    }

}
