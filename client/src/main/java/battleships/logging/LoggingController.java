package battleships.logging;

import static battleships.logging.ConfigValueName.IP;
import static battleships.logging.ConfigValueName.NAME;
import static battleships.logging.ConfigValueName.PORT;

import battleships.App;
import battleships.logging.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller of logging window.
 */
public class LoggingController implements Initializable {

  private App mainApp;
  private ResourceBundle resourceBundle;

  @FXML
  private Text playerNameText;

  @FXML
  private CheckBox checkBoxRandomShipPlacement;

  @FXML
  private TextField playerNameInput;

  @FXML
  private Text serverPortText;

  @FXML
  private Text serverIpText;

  @FXML
  private TextField serverPortInput;

  @FXML
  private TextField serverIpInput;

  @FXML
  private Button logInButton;

  @FXML
  void onActionLoginButton(ActionEvent event) {
    if (new Validator().validate(configFieldsValues())) {
      String host = configFieldsValues().get(IP).stringValue();
      int port = Integer.parseInt(configFieldsValues().get(PORT).stringValue());
      mainApp.submitLoggingData(host, port, resourceBundle);
    } else {
      invalidLoggingDataAlert();
    }
  }

  private Map<ConfigValueName, ConfigValue> configFieldsValues() {
    Map<ConfigValueName, ConfigValue> validationMap = new EnumMap<>(ConfigValueName.class);
    validationMap.put(IP, () -> serverIpInput.getText());
    validationMap.put(PORT, () -> serverPortInput.getText());
    validationMap.put(NAME, () -> playerNameInput.getText());
    return validationMap;
  }

  @FXML
  void polishVersion(ActionEvent event) {
    resourceBundle = ResourceBundle.getBundle(LanguageLoadOption.PL.toString());
    assignKeyTranslation();
  }

  @FXML
  void englishVersion(ActionEvent event) {
    resourceBundle = ResourceBundle.getBundle(LanguageLoadOption.EN.toString());
    assignKeyTranslation();
  }

  /**
   * Called to initialize a controller.
   *
   * @param location The location used to resolve relative paths for the root object,
   *                or null if the location is not known.
   * @param resources ResourceBundle delivering proper translation
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.resourceBundle = resources;
    playerNameInput.setText(resourceBundle.getString("PLAYER"));
    serverPortInput.setText("4321");
  }

  /**
   * Set text for elements of gui corresponding to selected language version.
   */
  public void assignKeyTranslation() {
    playerNameText.setText(resourceBundle.getString("PLAYER_NAME"));
    serverPortText.setText(resourceBundle.getString("SERVER_PORT"));
    serverIpText.setText(resourceBundle.getString("SERVER_IP"));
    logInButton.setText(resourceBundle.getString("LOG_IN"));
    checkBoxRandomShipPlacement.setText(resourceBundle.getString("RANDOM_SHIPS"));
  }

  /**
   * Sets reference to main class of application.
   */
  public void setMainApp(App mainApp) {
    this.mainApp = mainApp;
  }

  private void invalidLoggingDataAlert() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(resourceBundle.getString("WARNING_DIALOG"));
    alert.setHeaderText(resourceBundle.getString("INVALID_LOGGING_DATA"));
    alert.setContentText("...");

    alert.showAndWait();
  }
}

