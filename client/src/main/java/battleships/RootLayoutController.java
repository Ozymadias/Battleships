package battleships;

import battleships.communication.ClientHandler;
import battleships.game.Board;
import battleships.game.PlayerBoardViewController;
import battleships.game.ShipsRandomize;
import battleships.logger.BattleshipLog;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Arrays;

public class RootLayoutController {

    private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";
    private static final String OPPONENT_BOARD_VIEW_FXML = "/fxml/OpponentBoardViewLayout.fxml";

    BattleshipLog log = BattleshipLog.provideLogger(RootLayoutController.class);

    private ClientHandler clientHandler;

    @FXML
    BorderPane borderPane;

    @FXML
    private void initialize(){
        try{
            addOpponentBoardView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initWithFleet(ClientHandler clientHandler){
        this.clientHandler = clientHandler;
        ShipsRandomize shipsRandomize = ShipsRandomize.build(Board.build());
        Fleet fleet = shipsRandomize.placeAllFleet();
        Board board = shipsRandomize.getBoard();
        sendFleet(fleet);
        try {
            addPlayerBoardView(board, fleet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlayerBoardView(Board board, Fleet fleet) throws IOException {
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
    }

    public void sendFleet(Fleet fleet){
        log.info("preparing fleet to send");
        clientHandler.sendMessage(fleet);
    }

}
