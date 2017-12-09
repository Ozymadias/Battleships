package battleships.controller.validation;

import battleships.ConfigurationValueName;
import battleships.ConfigurationValue;

import java.util.Map;

import static battleships.ConfigurationValueName.*;

/**
 * Validator. Performs a validation of values that are passed.
 */
public class Validator {
    private final ConfigValidator nameValidator;
    private final ConfigValidator ipConfigValidator;
    private final ConfigValidator portConfigValidator;

    /**
     * Specific objects that implements ConfigurationValidator are created for purpose of later validation.
     */
    public Validator() {
        nameValidator = new NameValidator();
        ipConfigValidator = new IpConfigValidator();
        portConfigValidator = new PortConfigValidator();
    }

    /**
     * Accept EnumMap of ConfigurationValueName as a parameter, and returns a boolean result of evaluation of each parameters.
     */
    public boolean validate(Map<ConfigurationValueName, ConfigurationValue> configurationEnumMap) {
        return nameValidator.validate(configurationEnumMap.get(NAME))
                && ipConfigValidator.validate(configurationEnumMap.get(IP))
                && portConfigValidator.validate(configurationEnumMap.get(PORT));
    }
}
