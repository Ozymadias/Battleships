package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.Messagable;
import battleships.communication.messages.WelcomeMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RootLayoutController {

    private static final String PLAYER_BOARD_VIEW_FXML = "/fxml/PlayerBoardViewLayout.fxml";

    @FXML
    BorderPane borderPane;

    private ClientHandler clientHandler;

    @FXML
    private void initialize(){
        addPlayerBoardView();
    }

    public void waitForWelcomeMessage() {
        Messagable messagable;
        do {
            messagable = clientHandler.receiveMessage();
        } while (!(messagable instanceof WelcomeMessage));
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    private void addPlayerBoardView(){
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(PLAYER_BOARD_VIEW_FXML));
            BorderPane initLayout = loader.load();
            borderPane.setLeft(initLayout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
