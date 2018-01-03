package battleships.logging.validation;

import battleships.logging.ConfigValue;
import battleships.utils.BattleshipUtils;

/**
 * Takes ConfigValue as a parameter, than calls stringValue() and checks if result is not
 * empty String.
 */
class NameValidator implements ConfigurationValidator {

  /**
   * Calls stringValue() on passed parameter and checks if result is not
   * empty String as any String that is not empty is considered as valid name.
   */
  @Override
  public boolean validate(ConfigValue toValidate) {
    return !BattleshipUtils.checkIfStringIsEmpty(toValidate.stringValue());
  }
}
