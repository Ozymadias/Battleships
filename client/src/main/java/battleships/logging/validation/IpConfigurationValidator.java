package battleships.logging.validation;

import battleships.logging.ConfigValue;
import battleships.utils.BattleshipUtils;

import java.util.Arrays;


/**
 * IP address validator. Takes ConfigValue as a parameter, than splits String that is the result
 * of stringValue(); it into segments by IP_ADDRESS_REGEX. After that checks if every segment is
 * correct length, contains only numbers and is in correct range for IP address.
 */
class IpConfigurationValidator implements ConfigurationValidator {

  private static final int MIN_IP_VALUE = 0;
  private static final int MAX_IP_VALUE = 255;
  private static final int MIN_IP_SEGMENT_LENGTH = 0;
  private static final int MAX_IP_SEGMENT_LENGTH = 3;
  private static final String IP_ADDRESS_REGEX = "\\.";
  private static final int IP_SEGMENTS_COUNT = 4;

  /**
   * Check if IP segment contains only numbers.
   */
  private boolean isNumeric(String toValidate) {
    return BattleshipUtils.checkIfStringIsNumeric(toValidate);
  }

  /**
   * Check if IP segment length is correct.
   */
  private boolean hasCorrectLength(String toValidate) {
    return toValidate.length() > MIN_IP_SEGMENT_LENGTH
        && toValidate.length() <= MAX_IP_SEGMENT_LENGTH;
  }

  /**
   * Check if IP segment is in correct range.
   */
  private boolean isWithinCorrectRange(String toValidate) {
    Integer evaluationInteger = Integer.parseInt(toValidate);
    return evaluationInteger >= MIN_IP_VALUE && evaluationInteger <= MAX_IP_VALUE;

  }

  /**
   * It validates a given ConfigValue and checks if its a correct IP address.
   */
  @Override
  public boolean validate(ConfigValue toValidate) {
    Long evaluation = Arrays.stream(splitIpByDot(toValidate))
        .filter(this::hasCorrectLength)
        .filter(this::isNumeric)
        .filter(this::isWithinCorrectRange)
        .count();
    return evaluation == IP_SEGMENTS_COUNT;
  }

  /**
   * Splits IP by regex and creates array of Strings that represent IP segments.
   */
  private String[] splitIpByDot(ConfigValue toValidate) {
    return toValidate.stringValue().split(IP_ADDRESS_REGEX);
  }
}
