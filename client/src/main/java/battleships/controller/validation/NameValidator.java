package battleships.controller.validation;

import battleships.ConfigurationValue;

class NameValidator implements ConfigValidator {

    @Override
    public boolean validate(ConfigurationValue toValidate) {
        return !toValidate.stringValue().isEmpty();
    }
}
