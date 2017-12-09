package battleships.controller.validation;

import battleships.ConfigurationValue;
import org.apache.commons.lang.StringUtils;

/**
 * Port address validator. Takes ConfigurationValue as a parameter, than checks if the result of stringValue();
 * contains only numbers. When and only when it contains only numbers its parsed into Integer and check if its in bounds
 * of correct port value.
 */
class PortConfigValidator implements ConfigValidator {

    private static final int MIN_PORT_VALUE = 1024;
    private static final int MAX_PORT_VALUE = 65535;

    /**
     * Checks if the result of stringValue(); contains only numbers. When and only when it contains only numbers it
     * calls isInRange(); method than returns boolean value of both evaluation;
     */
    @Override
    public boolean validate(ConfigurationValue toValidate) {
        return StringUtils.isNumeric(toValidate.stringValue()) && isInRange(toValidate.stringValue());
    }

    /**
     * Parse integer and checks if its in correct range.
     */
    private boolean isInRange(String testString) {
        return Integer.parseInt(testString) > MIN_PORT_VALUE && Integer.parseInt(testString) <= MAX_PORT_VALUE;
    }
}
