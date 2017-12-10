package battleships.controller.validation;

import battleships.ConfigurationValue;

interface ConfigurationValidator {
    boolean validate(ConfigurationValue toValidate);
}
