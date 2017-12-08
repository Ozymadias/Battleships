package battleships.controller.validation;

import battleships.ConfigurationEnum;
import battleships.ConfigurationValue;

import java.util.Map;

import static battleships.ConfigurationEnum.*;

public class Validator {

    public boolean validate(Map<ConfigurationEnum, ConfigurationValue> configurationEnumMap) {
        NameValidator nameValidator = new NameValidator();
        IpConfigValidator ipConfigValidator = new IpConfigValidator();
        PortConfigValidator portConfigValidator = new PortConfigValidator();
        return nameValidator.validate(configurationEnumMap.get(NAME))
                && ipConfigValidator.validate(configurationEnumMap.get(IP))
                && portConfigValidator.validate(configurationEnumMap.get(PORT));
    }
}
