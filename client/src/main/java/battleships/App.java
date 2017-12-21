package battleships;

import battleships.logging.LoggingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application{
    private static final String APP_NAME = "BATTLESHIPS!";
    private static final String LOGIN_FXML = "/fxml/login.fxml";
    private static final String ROOT_LAYOUT_FXML = "/fxml/RootLayout.fxml";

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(APP_NAME);
        showLoginWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * loads logging form from fxml file
     * binds it with stage
     * and shows form
     */
    private void showLoginWindow(){
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(LOGIN_FXML));
            final AnchorPane initLayout = loader.load();
            primaryStage.setScene(new Scene(initLayout));
            final LoggingController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads game form from fxml file
     * binds it with stage
     * and shows form
     */
    private void initRootLayout(){
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(ROOT_LAYOUT_FXML));
            primaryStage.setScene(new Scene(loader.<BorderPane>load()));
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loggingSuccessful() {
        initRootLayout();
    }
}
