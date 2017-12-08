package battleships.controller.validation;

import battleships.ConfigurationValue;

interface ConfigValidator {
    boolean validate(ConfigurationValue toValidate);
}
