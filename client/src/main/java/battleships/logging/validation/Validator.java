package battleships.logging.validation;

import battleships.logging.ConfigurationValueName;
import battleships.logging.ConfigurationValue;

import java.util.Map;

import static battleships.logging.ConfigurationValueName.*;

/**
 * It performs a validation of values that are passed.
 */
public class Validator {
  private final ConfigurationValidator nameValidator;
  private final ConfigurationValidator ipConfigurationValidator;
  private final ConfigurationValidator portConfigurationValidator;

  /**
   * Specific objects that implements ConfigurationValidator are created for purpose of later validation.
   */
  public Validator() {
    nameValidator = new NameValidator();
    ipConfigurationValidator = new IpConfigurationValidator();
    portConfigurationValidator = new PortConfigurationValidator();
  }

  /**
   * Accept EnumMap of ConfigurationValueName as a parameter, and returns a boolean result of evaluation of each parameters.
   */
  public boolean validate(Map<ConfigurationValueName, ConfigurationValue> configurationEnumMap) {
    return nameValidator.validate(configurationEnumMap.get(NAME))
        && ipConfigurationValidator.validate(configurationEnumMap.get(IP))
        && portConfigurationValidator.validate(configurationEnumMap.get(PORT));
  }
}
