package battleships;

import battleships.logging.LoggingController;
import battleships.root.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Gui for BATTLESHIP! game.
 */
public class App extends Application{
    private static final String APP_NAME = "BATTLESHIPS!";
    private static final String LOGIN_FXML = "/fxml/login.fxml";
    private static final String ROOT_LAYOUT_FXML = "/fxml/RootLayout.fxml";

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(APP_NAME);
        showLoginWindow();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void showLoginWindow(){
        try {
            // Load login window form fxml file
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(LOGIN_FXML));

            AnchorPane initLayout = (AnchorPane) loader.load();

            // Show the scene containing the init layout.
            Scene scene = new Scene(initLayout);
            primaryStage.setScene(scene);

            // Give the logging access to the main app.
            LoggingController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(ROOT_LAYOUT_FXML));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the logging access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loggingSuccessful() {
        initRootLayout();
    }
}
