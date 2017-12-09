package battleships.controller;

import battleships.ConfigurationValueName;
import battleships.ConfigurationValue;
import battleships.LanguageLoadOption;
import battleships.LanguageVersion;
import battleships.controller.validation.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.EnumMap;
import java.util.Map;

import static battleships.ConfigurationValueName.*;
import static battleships.LanguageLoadOption.EN;
import static battleships.LanguageLoadOption.PL;
import static battleships.Translation.*;

public class ConfigurationController {
    private LanguageVersion languageVersion;

    @FXML
    private Button polishButton;

    @FXML
    private Button englishButton;

    @FXML
    private Text playerNameText;

    @FXML
    private CheckBox checkBoxRandomShipPlacement;

    @FXML
    private TextArea playerNameInput;

    @FXML
    private Text serverPortText;

    @FXML
    private Text serverIPText;

    @FXML
    private TextArea serverPortInput;

    @FXML
    private TextArea serverIPInput;

    @FXML
    private Button logInButton;

    @FXML
    void OnActionLoginButton(ActionEvent event) {
        Validator validator = new Validator();
        System.out.println("FOR TESTING PURPOSE VALIDATION IS: " + validator.validate(configFieldsValues()));
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
}

