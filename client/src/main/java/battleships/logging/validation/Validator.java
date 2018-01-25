package battleships.logging.validation;

import static battleships.logging.ConfigValueName.IP;
import static battleships.logging.ConfigValueName.NAME;
import static battleships.logging.ConfigValueName.PORT;

import battleships.logging.ConfigValue;
import battleships.logging.ConfigValueName;

import java.util.Map;



/**
 * It performs a validation of values that are passed.
 */
public class Validator {
  private final ConfigurationValidator nameValidator;
  private final ConfigurationValidator ipConfigurationValidator;
  private final ConfigurationValidator portConfigurationValidator;

  /**
   * Specific objects that implements ConfigurationValidator are created for purpose of
   * later validation.
   */
  public Validator() {
    nameValidator = new NameValidator();
    ipConfigurationValidator = new IpConfigurationValidator();
    portConfigurationValidator = new PortConfigurationValidator();
  }

  /**
   * Accept EnumMap of ConfigValueName as a parameter, and returns a boolean result of
   * evaluation of each parameters.
   * @param configurationEnumMap map of configurationNames as enums and it's values
   * @return true if all of configuration values are proper, false otherwise
   */
  public boolean validate(Map<ConfigValueName, ConfigValue> configurationEnumMap) {
    return nameValidator.validate(configurationEnumMap.get(NAME))
        && ipConfigurationValidator.validate(configurationEnumMap.get(IP))
        && portConfigurationValidator.validate(configurationEnumMap.get(PORT));
  }
}
