package battleships.controller.validation;


import battleships.ConfigurationValue;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

class IpConfigValidator implements ConfigValidator {

    private boolean isNumeric(String validationString) {
        return StringUtils.isNumeric(validationString);
    }

    private boolean isCorrectIPValue(String validationString) {
        return validationString.length() > 0 && validationString.length() <= 3;
    }

    private boolean isInCorrectRange(String validationString) {
        Integer evaluationInteger = Integer.parseInt(validationString);
        return evaluationInteger >= 0 && evaluationInteger <= 255;

    }


    @Override
    public boolean validate(ConfigurationValue toValidate) {
        String[] parseArray = toValidate.stringValue().split("\\.");
        Long evaluationLong = Arrays.stream(parseArray)
                .filter(this::isCorrectIPValue)
                .filter(this::isNumeric)
                .filter(this::isInCorrectRange)
                .count();
        return evaluationLong == 4;
    }
}
