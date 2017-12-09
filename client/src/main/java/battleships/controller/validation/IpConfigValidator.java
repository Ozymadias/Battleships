package battleships.controller.validation;


import battleships.ConfigurationValue;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * IP address validator. Takes ConfigurationValue as a parameter, than splits String that is the result of stringValue();
 * it into segments by IP_ADDRESS_REGEX. After that checks if every segment is correct length, contains only numbers and
 * is in correct range for IP address.
 */
class IpConfigValidator implements ConfigValidator {

    private static final int MIN_IP_VALUE = 0;
    private static final int MAX_IP_VALUE = 255;
    private static final int MIN_IP_SEGMENT_LENGTH = 0;
    private static final int MAX_IP_SEGMENT_LENGTH = 3;
    private static final String IP_ADDRESS_REGEX = "\\.";
    private static final int IP_SEGMENTS_COUNT = 4;

    /**
     * Check if IP segment contains only numbers
     */
    private boolean isNumeric(String validationString) {
        return StringUtils.isNumeric(validationString);
    }

    /**
     * Check if IP segment length is correct
     */
    private boolean isInCorrectLength(String validationString) {
        return validationString.length() > MIN_IP_SEGMENT_LENGTH && validationString.length() <= MAX_IP_SEGMENT_LENGTH;
    }

    /**
     * Check if IP segment is in correct range
     */
    private boolean isInCorrectRange(String validationString) {
        Integer evaluationInteger = Integer.parseInt(validationString);
        return evaluationInteger >= MIN_IP_VALUE && evaluationInteger <= MAX_IP_VALUE;

    }

    /**
     * Preforms validation of given ConfigurationValue and checks if its a correct IP value
     */
    @Override
    public boolean validate(ConfigurationValue toValidate) {
        Long evaluationLong = Arrays.stream(splitIPByDot(toValidate))
                .filter(this::isInCorrectLength)
                .filter(this::isNumeric)
                .filter(this::isInCorrectRange)
                .count();
        return evaluationLong == IP_SEGMENTS_COUNT;
    }

    /**
     * Splits IP by regex and creates array of Strings that represent IP segments
     */
    private String[] splitIPByDot(ConfigurationValue toValidate) {
        return toValidate.stringValue().split(IP_ADDRESS_REGEX);
    }
}
