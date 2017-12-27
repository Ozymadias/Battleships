package battleships.logging.validation;

import battleships.logging.ConfigurationValue;
import battleships.utils.BattleshipUtils;

/**
 * Takes ConfigurationValue as a parameter, than checks if contains only numbers. When and only when it contains only
 * numbers its parsed into Integer and check if its within range of correct port value.
 */
class PortConfigurationValidator implements ConfigurationValidator {

  private static final int MIN_PORT_VALUE = 1024;
  private static final int MAX_PORT_VALUE = 65535;

  /**
   * Checks if the result of stringValue(); contains only numbers. When and only when it contains only numbers it
   * calls isWithinRange(); method than returns boolean value of both evaluation;
   */
  @Override
  public boolean validate(ConfigurationValue toValidate) {
    return BattleshipUtils.checkIfStringIsNumeric(toValidate.stringValue()) && isWithinRange(toValidate.stringValue());
  }

  /**
   * Parse integer and test if it is within correct range.
   */
  private boolean isWithinRange(String testString) {
    return Integer.parseInt(testString) > MIN_PORT_VALUE && Integer.parseInt(testString) <= MAX_PORT_VALUE;
  }
}
