package battleships.logging;

import battleships.App;
import battleships.logging.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

import static battleships.logging.ConfigurationValueName.*;

public class LoggingController {

    private App mainApp;

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
    void onActionLoginButton(ActionEvent event){
        if(new Validator().validate(configFieldsValues())){
            mainApp.loggingSuccessful(configFieldsValues());
        }else{
            invalidLoggingDataAlert();
        }
    }

    private Map<ConfigurationValueName, ConfigurationValue> configFieldsValues() {
        Map<ConfigurationValueName, ConfigurationValue> validationMap = new EnumMap<>(ConfigurationValueName.class);
        validationMap.put(IP, () -> serverIPInput.getText());
        validationMap.put(PORT, () -> serverPortInput.getText());
        validationMap.put(NAME, () -> playerNameInput.getText());
        return validationMap;
    }

    @FXML
    void polishVersion(ActionEvent event) {
        mainApp.languageLoadOption = LanguageLoadOption.PL;
        assignKeyTranslation();
    }

    @FXML
    void englishVersion(ActionEvent event) {
        mainApp.languageLoadOption = LanguageLoadOption.EN;
        assignKeyTranslation();
    }


    @FXML
    void initialize() {
        playerNameInput.setText("player");
        serverPortInput.setText("4321");
        serverIPInput.setText("127.0.0.1");
    }

    public void assignKeyTranslation() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(mainApp.languageLoadOption.toString());
        playerNameText.setText(resourceBundle.getString("PLAYER_NAME"));
        serverPortText.setText(resourceBundle.getString("SERVER_PORT"));
        serverIPText.setText(resourceBundle.getString("SERVER_IP"));
        logInButton.setText(resourceBundle.getString("LOG_IN"));
        checkBoxRandomShipPlacement.setText(resourceBundle.getString("RANDOM_SHIPS"));
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    private void invalidLoggingDataAlert(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(mainApp.languageLoadOption.toString());

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(resourceBundle.getString("WARNING_DIALOG"));
        alert.setHeaderText(resourceBundle.getString("INVALID_LOGGING_DATA"));
        alert.setContentText("...");

        alert.showAndWait();
    }
}

