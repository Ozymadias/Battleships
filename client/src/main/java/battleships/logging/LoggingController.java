package battleships.logging;

import battleships.*;
import battleships.logging.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import static battleships.logging.ConfigurationValueName.*;
import static battleships.logging.LanguageLoadOption.EN;
import static battleships.logging.LanguageLoadOption.PL;
import static battleships.LocalizationStringMarker.*;

public class LoggingController {
    private LanguageVersion languageVersion;

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
    void OnActionLoginButton(ActionEvent event) {
        if(new Validator().validate(configFieldsValues())){
            mainApp.loggingSuccessful();
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
    void PolishVersion(ActionEvent event) {
        initialize(PL);
    }

    @FXML
    void EnglishVersion(ActionEvent event) {
        initialize(EN);
    }


    @FXML
    void initialize() {
        languageVersion = new LanguageVersion(EN);
        assignKeyTranslation();
    }

    private void assignKeyTranslation() {
        playerNameText.setText(languageVersion.provideTranslation(PLAYER_NAME));
        serverPortText.setText(languageVersion.provideTranslation(SERVER_PORT));
        serverIPText.setText(languageVersion.provideTranslation(SERVER_IP));
        logInButton.setText(languageVersion.provideTranslation(LOG_IN));
        checkBoxRandomShipPlacement.setText(languageVersion.provideTranslation(RANDOM_SHIPS));
    }

    @FXML
    private void initialize(LanguageLoadOption languageLoadOption) {
        languageVersion = new LanguageVersion(languageLoadOption);
        assignKeyTranslation();
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    private void invalidLoggingDataAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText("Logging data is invalid");
        alert.setContentText("...");

        alert.showAndWait();
    }
}

