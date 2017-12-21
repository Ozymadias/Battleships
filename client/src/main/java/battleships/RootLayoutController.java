package battleships;

import battleships.communication.ClientHandler;
import battleships.game.PlayerBoardViewController;
import battleships.logging.LoggingController;
import battleships.ships.Fleet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayoutController {

    private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";
    private static final String OPPONENT_BOARD_VIEW_FXML = "/fxml/OpponentBoardViewLayout.fxml";

    private ClientHandler clientHandler;

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    @FXML
    BorderPane borderPane;

    @FXML
    private void initialize(){
        try{
            addPlayerBoardView();
            addOpponentBoardView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlayerBoardView() throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(PLAYER_BOARD_VIEW_FXML));
        final PlayerBoardViewController controller = loader.getController();
        borderPane.setLeft(loader.<BorderPane>load());
        final Fleet fleet = controller.getFleet();
    }

    private void addOpponentBoardView() throws IOException {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(OPPONENT_BOARD_VIEW_FXML));
        borderPane.setRight(loader.<BorderPane>load());
    }
}
