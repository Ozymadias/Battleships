package battleships;

import battleships.communication.ClientHandler;
import battleships.communication.ClientHandlerBuilder;
import battleships.controller.communication.ServerLoginManager;
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

public class App extends Application{
    private static final String APP_NAME = "BATTLESHIPS!";
    private static final String LOGIN_FXML = "/fxml/login.fxml";
    private static final String ROOT_LAYOUT_FXML = "/fxml/RootLayout.fxml";

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ServerLoginManager loginManager;

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
    public void showLoginWindow(){
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
     * @param clientHandler
     */
    public void initRootLayout(ClientHandler clientHandler){
        try {
            final FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource(ROOT_LAYOUT_FXML));
            rootLayout = loader.load();
            final RootLayoutController controller = loader.getController();
            controller.setClientHandler(clientHandler);
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loggingSuccessful(Map<ConfigurationValueName, ConfigurationValue> loggingDataMap){
        String host = loggingDataMap.get(ConfigurationValueName.IP).stringValue();
        String port = loggingDataMap.get(ConfigurationValueName.PORT).stringValue();
        Socket socket = null;
        try {
            socket = new Socket(host,Integer.parseInt(port));
            ClientHandler clientHandler = new ClientHandlerBuilder().setSocket(socket).addMessageReceiver().addMessageSender().build();
            initRootLayout(clientHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
