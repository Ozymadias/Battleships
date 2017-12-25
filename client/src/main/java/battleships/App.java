package battleships;

import battleships.communication.DataBus;
import battleships.communication.ServerComm;
import battleships.logger.BattleshipLog;
import battleships.logging.ConfigurationValue;
import battleships.logging.ConfigurationValueName;
import battleships.logging.LanguageLoadOption;
import battleships.logging.LoggingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import static battleships.logging.ConfigurationValueName.IP;
import static battleships.logging.ConfigurationValueName.PORT;

public class App extends Application {
    private static final String APP_NAME = "BATTLESHIPS!";
    private static final String LOGIN_FXML = "/fxml/login.fxml";
    private static final String ROOT_LAYOUT_FXML = "/fxml/RootLayout.fxml";
    private final BattleshipLog log = BattleshipLog.provideLogger(App.class);

    public LanguageLoadOption languageLoadOption = LanguageLoadOption.EN;

    private Stage primaryStage;

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
            controller.assignKeyTranslation();
            primaryStage.show();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * loads game form from fxml file
     * binds it with stage
     * and shows form
     */
    private void initRootLayout() {
        try {
            final ResourceBundle resourceBundle = ResourceBundle.getBundle(languageLoadOption.toString());
            final FXMLLoader loader = new FXMLLoader();
            loader.setResources(resourceBundle);
            loader.setLocation(App.class.getResource(ROOT_LAYOUT_FXML));
            BorderPane rootLayout = loader.load();
            final RootLayoutController controller = loader.getController();
            controller.setResourceBundle(resourceBundle);
            controller.init();
            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void loggingSuccessful(Map<ConfigurationValueName, ConfigurationValue> loggingDataMap) {
        String host = loggingDataMap.get(IP).stringValue();
        String port = loggingDataMap.get(PORT).stringValue();
        try {
            setUpConnection(host, Integer.parseInt(port));
            initRootLayout();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void setUpConnection(String host, Integer port) throws IOException {
        ServerComm serverComm = ServerComm.build(host, port);
        serverComm.init();
        DataBus.getInstance().subscribeMember(serverComm);
        DataBus.getInstance().subscribePublisher(serverComm);
    }
}
