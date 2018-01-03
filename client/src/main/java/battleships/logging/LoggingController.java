package battleships.logging;

import battleships.App;
import battleships.logging.validation.Validator;
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

import static battleships.logging.ConfigurationValueName.*;

//TODO: document me master!
public class LoggingController implements Initializable {

  private App mainApp;
  private ResourceBundle resourceBundle;

  @FXML
  private Button polishButton;

  @FXML
  private Button englishButton;

  @FXML
  private Text playerNameText;

  @FXML
  private CheckBox checkBoxRandomShipPlacement;

  @FXML
  private TextField playerNameInput;

  @FXML
  private Text serverPortText;

  @FXML
  private Text serverIPText;

  @FXML
  private TextField serverPortInput;

  @FXML
  private TextField serverIPInput;

  @FXML
  private Button logInButton;

  @FXML
  void onActionLoginButton() {
    if (new Validator().validate(configFieldsValues())) {
      mainApp.loggingSuccessful(configFieldsValues(), resourceBundle);
    } else {
      invalidLoggingDataAlert();
    }
  }

  //TODO: separate logic from UI - pass texts to map, not use map here
  private Map<ConfigurationValueName, ConfigurationValue> configFieldsValues() {
    Map<ConfigurationValueName, ConfigurationValue> validationMap = new EnumMap<>(ConfigurationValueName.class);
    validationMap.put(IP, () -> serverIPInput.getText());
    validationMap.put(PORT, () -> serverPortInput.getText());
    validationMap.put(NAME, () -> playerNameInput.getText());
    return validationMap;
  }

  //TODO: redundant event here and there
  @FXML
  void polishVersion() {
    resourceBundle = ResourceBundle.getBundle(LanguageLoadOption.PL.toString());
    assignKeyTranslation();
  }

  @FXML
  void englishVersion() {
    resourceBundle = ResourceBundle.getBundle(LanguageLoadOption.EN.toString());
    assignKeyTranslation();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.resourceBundle = resources;
    //TODO: this should go in a bundle and be localized, so in PL version you won't see "player" :P
    playerNameInput.setText("player");
    serverPortInput.setText("4321");
  }

  public void assignKeyTranslation() {
    playerNameText.setText(resourceBundle.getString("PLAYER_NAME"));
    serverPortText.setText(resourceBundle.getString("SERVER_PORT"));
    serverIPText.setText(resourceBundle.getString("SERVER_IP"));
    logInButton.setText(resourceBundle.getString("LOG_IN"));
    checkBoxRandomShipPlacement.setText(resourceBundle.getString("RANDOM_SHIPS"));
  }

  public void setMainApp(App mainApp) {
    this.mainApp = mainApp;
  }

  private void invalidLoggingDataAlert() {
	  //TODO: look for a constructor with more params, instead of using setters all the time
	  //TODO: also, if you create various alerts all the time, how about your own code that does that? Alerts, Warnings, Confirmations etc.
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(resourceBundle.getString("WARNING_DIALOG"));
    alert.setHeaderText(resourceBundle.getString("INVALID_LOGGING_DATA"));
    alert.setContentText("...");

    alert.showAndWait();
  }
}

