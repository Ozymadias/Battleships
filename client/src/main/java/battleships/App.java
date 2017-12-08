package battleships;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gui for BATTLESHIP! game.
 */
public class App extends Application{
    private static final String APP_NAME = "BATTLESHIPS!";
    private static final String LOGIN_FXML = "/fxml/login.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(LOGIN_FXML));
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
