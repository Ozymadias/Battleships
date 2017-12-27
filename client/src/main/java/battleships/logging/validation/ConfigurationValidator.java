package battleships.logging.validation;

import battleships.logging.ConfigurationValue;

interface ConfigurationValidator {
  boolean validate(ConfigurationValue toValidate);
}
