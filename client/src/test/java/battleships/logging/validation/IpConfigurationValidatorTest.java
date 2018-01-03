package battleships.logging.validation;

import battleships.logging.ConfigValue;
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

    return new Object[][] {
        {(ConfigValue) () -> "wololo", false},
        {(ConfigValue) () -> "0.0.0.0", true},
        {(ConfigValue) () -> "255.255.255.0", true},
        {(ConfigValue) () -> "10.10.10.33", true},
        {(ConfigValue) () -> "10.0.r.33", false},
        {(ConfigValue) () -> "10.0.0.3453", false},
        {(ConfigValue) () -> "wololo", false},
        {(ConfigValue) () -> "You.Are.Not.Stupid", false},
        {(ConfigValue) () -> "", false},
        {(ConfigValue) () -> "10.10.10.10.10", false},
        {(ConfigValue) () -> "340.42", false},
        {(ConfigValue) () -> "33.42.34.", false},
        {(ConfigValue) () -> "33.422.34", false},
        {(ConfigValue) () -> "33.22.34", false},
        {(ConfigValue) () -> "333.22.34.523", false},
        {(ConfigValue) () -> "100334", false},
    };
  }

  @Test(dataProvider = "ipPool")
  public void whenValidateIP_expectItIsInCorrectFormatOrNot(ConfigValue ipToValidate, Boolean expectedResult) {
    //when
    boolean isIPValid = ipValidator.validate(ipToValidate);
    //then
    assertThat(isIPValid).isEqualTo(expectedResult);
  }
}