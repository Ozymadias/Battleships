package battleships;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayoutController {

    private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";

    @FXML
    BorderPane borderPane;

    // Reference to the main application
    private App mainApp;

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    /*
     * automatically called after fxml file has been loaded
     */
    @FXML
    private void initialize(){
        addPlayerBoardView();
    }

    private void addPlayerBoardView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(PLAYER_BOARD_VIEW_FXML));

            BorderPane initLayout = (BorderPane) loader.load();
            borderPane.setLeft(initLayout);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
