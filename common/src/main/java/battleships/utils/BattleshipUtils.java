package battleships.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Random;

public class BattleshipUtils {
  private BattleshipUtils() {
  }

  public static boolean checkIfStringIsNumeric(String toCheck) {
    return StringUtils.isNumeric(toCheck);
  }

  public static boolean checkIfStringIsEmpty(String toCheck) {
    return StringUtils.isEmpty(toCheck);
  }

  public static String provideEmptyString() {
    return StringUtils.EMPTY;
  }

  public static int provideRandomNumber(int min, int max) {
    return new Random().nextInt((max - min) + 1) + min;
  }
}
