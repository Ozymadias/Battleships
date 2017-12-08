package battleships.controller.validation;

import battleships.ConfigurationValue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NameValidatorTest {
    private NameValidator nameValidator;

    @BeforeTest

    private void setUp() {
        nameValidator = new NameValidator();
    }

    @DataProvider(name = "namePool")
    private static Object[][] namePool() {

        return new Object[][]{
                {(ConfigurationValue) () -> "wololo", true},
                {(ConfigurationValue) () -> "Iga", true},
                {(ConfigurationValue) () -> "Krzysztof", true},
                {(ConfigurationValue) () -> "", false},
                {(ConfigurationValue) () -> "Blob zabojca z kosmosu", true},
        };
    }

    @Test(dataProvider = "namePool")
    public void shouldPassWhenValidationOfNameThatIsOtherThanNullIsCorrect(ConfigurationValue stringToValidate, boolean expectedResult) throws Exception {
        assertThat(nameValidator.validate(stringToValidate)).isEqualTo(expectedResult);
    }
}