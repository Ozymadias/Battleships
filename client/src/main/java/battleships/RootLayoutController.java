package battleships;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayoutController {

    private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";
    private static final String OPPONENT_BOARD_VIEW_FXML = "/fxml/OpponentBoardViewLayout.fxml";

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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(PLAYER_BOARD_VIEW_FXML));
        borderPane.setLeft(loader.<BorderPane>load());
    }

    private void addOpponentBoardView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(OPPONENT_BOARD_VIEW_FXML));
        borderPane.setRight(loader.<BorderPane>load());
    }
}
