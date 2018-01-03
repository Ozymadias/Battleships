package battleships.logging.validation;

import battleships.logging.ConfigValue;

interface ConfigurationValidator {
  boolean validate(ConfigValue toValidate);
}
