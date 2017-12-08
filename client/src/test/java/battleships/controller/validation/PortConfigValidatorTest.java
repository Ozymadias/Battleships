package battleships.controller.validation;

import battleships.ConfigurationValue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PortConfigValidatorTest {
    private PortConfigValidator portConfigValidator;

    @BeforeTest

    private void setUp() {
        portConfigValidator = new PortConfigValidator();
    }

    @DataProvider(name = "portPool")
    private static Object[][] portPool() {

        return new Object[][]{
                {(ConfigurationValue) () ->"maksiu", false},
                {(ConfigurationValue) ()->"0.0.0.0", false},
                {(ConfigurationValue) ()->"Iga", false},
                {(ConfigurationValue) ()->"Krzychu", false},
                {(ConfigurationValue) ()->"Robert", false},
                {(ConfigurationValue) ()->"0", false},
                {(ConfigurationValue) ()->"666", false},
                {(ConfigurationValue) ()->"1666", true},
                {(ConfigurationValue) ()->"65535", true},
                {(ConfigurationValue) ()->"6535", true},
                {(ConfigurationValue) ()->"4535", true},
                {(ConfigurationValue) ()->"6635", true},
                {(ConfigurationValue) ()->"65536", false},
                {(ConfigurationValue) ()->"65.36", false},
                {(ConfigurationValue) ()->"w2415", false},
                {(ConfigurationValue) ()->"423415", false},
                {(ConfigurationValue) ()->"-423415", false},
                {(ConfigurationValue) ()->"-3415", false},
        };
    }

    @Test(dataProvider = "portPool")
    public void shouldPassWhenValidatorPerformsCorrectValidationOnGivenData(ConfigurationValue portStringToValidate, boolean expectedValidationResult) throws Exception {
        assertThat(portConfigValidator.validate(portStringToValidate)).isEqualTo(expectedValidationResult);
    }
}