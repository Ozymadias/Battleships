package battleships.logging.validation;

import battleships.logging.ConfigurationValue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IpConfigurationValidatorTest {
    private IpConfigurationValidator ipValidator;

    @BeforeTest

    private void setUp() {
        ipValidator = new IpConfigurationValidator();
    }

    @DataProvider(name = "ipPool")
    private static Object[][] ipPool() {

        return new Object[][]{
                {(ConfigurationValue) () -> "wololo", false},
                {(ConfigurationValue) () -> "0.0.0.0", true},
                {(ConfigurationValue) () -> "255.255.255.0", true},
                {(ConfigurationValue) () -> "10.10.10.33", true},
                {(ConfigurationValue) () -> "10.0.r.33", false},
                {(ConfigurationValue) () -> "10.0.0.3453", false},
                {(ConfigurationValue) () -> "wololo", false},
                {(ConfigurationValue) () -> "You.Are.Not.Stupid", false},
                {(ConfigurationValue) () -> "", false},
                {(ConfigurationValue) () -> "10.10.10.10.10", false},
                {(ConfigurationValue) () -> "340.42", false},
                {(ConfigurationValue) () -> "33.42.34.", false},
                {(ConfigurationValue) () -> "33.422.34", false},
                {(ConfigurationValue) () -> "33.22.34", false},
                {(ConfigurationValue) () -> "333.22.34.523", false},
                {(ConfigurationValue) () -> "100334", false},
        };
    }

    @Test(dataProvider = "ipPool")
    public void shouldPassIfPerformsCorrectValidationOfGivenData(ConfigurationValue ipToValidate, Boolean expectedResult) throws Exception {
        assertThat(ipValidator.validate(ipToValidate)).isEqualTo(expectedResult);
    }
}