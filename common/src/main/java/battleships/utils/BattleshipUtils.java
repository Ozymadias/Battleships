package battleships.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

/**
 * This is a class that contains helpful utils.
 */
public class BattleshipUtils {
  private BattleshipUtils() {
  }

  /**
   * It checks if String is numeric.
   */
  public static boolean checkIfStringIsNumeric(String toCheck) {
    return StringUtils.isNumeric(toCheck);
  }

  /**
   * It checks if String is empty.
   */
  public static boolean checkIfStringIsEmpty(String toCheck) {
    return StringUtils.isEmpty(toCheck);
  }

  /**
   * It provides empty String.
   */
  public static String provideEmptyString() {
    return StringUtils.EMPTY;
  }

  /**
   * This method provides random integer between min and max (inclusive).
   */
  public static int provideRandomNumber(int min, int max) {
    return new Random().nextInt((max - min) + 1) + min;
  }
}
