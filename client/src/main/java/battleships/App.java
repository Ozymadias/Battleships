package battleships;

import battleships.communication.DataBus;
import battleships.communication.ServerComm;
import battleships.logging.ConfigurationValue;
import battleships.logging.ConfigurationValueName;
import battleships.logging.LoggingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import static battleships.logging.ConfigurationValueName.IP;
import static battleships.logging.ConfigurationValueName.PORT;

public class App extends Application {
    private static final String APP_NAME = "BATTLESHIPS!";
    private static final String LOGIN_FXML = "/fxml/login.fxml";
    private static final String ROOT_LAYOUT_FXML = "/fxml/RootLayout.fxml";

    private Stage primaryStage;
    private ServerComm serverComm;

    @Override
    public void start(Stage primaryStage){
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
    private void initRootLayout() {
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(ROOT_LAYOUT_FXML));
            BorderPane rootLayout = loader.load();
            RootLayoutController controller = loader.getController();
            controller.init();
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loggingSuccessful(Map<ConfigurationValueName, ConfigurationValue> loggingDataMap) {
        String host = loggingDataMap.get(IP).stringValue();
        String port = loggingDataMap.get(PORT).stringValue();
        try {
            setUpConnection(host, Integer.parseInt(port));
            initRootLayout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpConnection(String host, Integer port) throws IOException {
        this.serverComm = ServerComm.build(host, port);
        DataBus.getInstance().subscribe(serverComm);
    }
}
