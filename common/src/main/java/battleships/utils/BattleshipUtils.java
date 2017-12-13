package battleships.utils;

import org.apache.commons.lang.StringUtils;

public class BattleshipUtils {

    public static boolean checkIfStringIsNumeric(String toCheck) {
        return StringUtils.isNumeric(toCheck);
    }

    public static boolean checkIfStringIsEmpty(String toCheck) {
        return StringUtils.isEmpty(toCheck);
    }

    public static String provideEmptyString() {
        return StringUtils.EMPTY;
    }
}
