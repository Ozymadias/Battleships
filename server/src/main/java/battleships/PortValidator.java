package battleships;

import battleships.utils.BattleshipUtils;

class PortValidator {
  private static final int MIN_PORT_VALUE = 1024;
  private static final int MAX_PORT_VALUE = 65535;

  /**
   * Checks if passed stringValue is valid port number.
   * @param portStringValue string value of port to validate
   * @return true if port value is valid, false otherwise
   */
  boolean validate(String portStringValue) {
    return BattleshipUtils.checkIfStringIsNumeric(portStringValue)
        && isWithinRange(portStringValue);
  }

  private boolean isWithinRange(String testString) {
    return Integer.parseInt(testString) > MIN_PORT_VALUE
        && Integer.parseInt(testString) <= MAX_PORT_VALUE;
  }
}
