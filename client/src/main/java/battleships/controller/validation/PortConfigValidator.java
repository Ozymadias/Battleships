package battleships.controller.validation;

import battleships.ConfigurationValue;
import org.apache.commons.lang.StringUtils;

class PortConfigValidator implements ConfigValidator {
    @Override
    public boolean validate(ConfigurationValue toValidate) {
        return StringUtils.isNumeric(toValidate.stringValue()) && isInRange(toValidate.stringValue());
    }

    private boolean isInRange(String testString) {
        return Integer.parseInt(testString)>1024&&Integer.parseInt(testString)<=65535;
    }
}
