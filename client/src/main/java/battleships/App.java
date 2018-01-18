package battleships;

import battleships.communication.server.ServerConnector;
import battleships.logger.BattleshipLog;
import battleships.logging.LanguageLoadOption;
import battleships.logging.LoggingController;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Main application class.
 */
public class App extends Application {
  private static final String APP_NAME = "BATTLESHIPS!";
  private static final String LOGIN_FXML = "/fxml/login.fxml";
  private static final String ROOT_LAYOUT_FXML = "/fxml/RootLayout.fxml";
  private final BattleshipLog log = BattleshipLog.provideLogger(App.class);

  private ResourceBundle resourceBundle = ResourceBundle
      .getBundle(LanguageLoadOption.EN.toString());

  private Stage primaryStage;

  /**
   * Starts client application.
   * @param args args are not used
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Method automatically called when the application is launched
   * show window for logging to application.
   * @param primaryStage the main container for JavaFX application
   */
  @Override
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle(APP_NAME);
    showLoginWindow();
  }

  /*
   * loads logging form from fxml file
   * binds it with stage
   * and shows form.
   */
  private void showLoginWindow() {
    try {
      final FXMLLoader loader = new FXMLLoader();
      loader.setResources(resourceBundle);
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

  /*
   * loads game form from fxml file
   * binds it with stage
   * and shows form.
   */
  private void initRootLayout() {
    try {
      final FXMLLoader loader = new FXMLLoader();
      loader.setResources(this.resourceBundle);
      loader.setLocation(App.class.getResource(ROOT_LAYOUT_FXML));
      BorderPane rootLayout = loader.load();
      primaryStage.setScene(new Scene(rootLayout));
      primaryStage.setResizable(false);
      primaryStage.show();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * Tries to log in to the server with given host and port data.
   * Also set chosen resource bundle which points to selected language for application.
   * @param host server to which application should connect
   * @param port server port on which connection should be established
   * @param bundle ResourceBundle loaded from resources, pointing to used language
   */
  public void submitLoggingData(String host, int port, ResourceBundle bundle) {
    this.resourceBundle = bundle;
    setUpConnection(host, port);
  }

  private void runTask(String host, int port) {
    Task<Integer> task = new Task<Integer>() {
      @Override protected Integer call() throws Exception {
        boolean connected = false;
        do {
          if (isCancelled()) {
            break;
          }
          ServerConnector serverConnector = ServerConnector.buildWithHostAndPort(host, port);
          Thread.sleep(300);
          connected = serverConnector.setUp();
        } while (!connected);
        return 0;
      }
    };

    AlertWithProgressIndicator alertWithProgressIndicator
        = AlertWithProgressIndicator.asInstance(Alert.AlertType.INFORMATION,
        resourceBundle.getString("CONNECTING"),
        resourceBundle.getString("RESPONSE_WAITING"));

    task.setOnScheduled(e -> {
      alertWithProgressIndicator.show();
    });

    task.setOnSucceeded(e -> {
      alertWithProgressIndicator.close();
      initRootLayout();
    });

    Thread th = new Thread(task);
    th.setDaemon(true);
    th.start();
  }

  private void setUpConnection(String host, Integer port) {
    runTask(host, port);
  }
}
