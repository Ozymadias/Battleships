package battleships.logging.validation;

import battleships.logging.ConfigValue;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PortConfigurationValidatorTest {
  private PortConfigurationValidator portConfigValidator;

  @BeforeTest

  private void setUp() {
    portConfigValidator = new PortConfigurationValidator();
  }

  @DataProvider(name = "portPool")
  private static Object[][] portPool() {

    return new Object[][] {
        {(ConfigValue) () -> "maksiu", false},
        {(ConfigValue) () -> "0.0.0.0", false},
        {(ConfigValue) () -> "Iga", false},
        {(ConfigValue) () -> "Krzychu", false},
        {(ConfigValue) () -> "Robert", false},
        {(ConfigValue) () -> "0", false},
        {(ConfigValue) () -> "666", false},
        {(ConfigValue) () -> "1666", true},
        {(ConfigValue) () -> "65535", true},
        {(ConfigValue) () -> "6535", true},
        {(ConfigValue) () -> "4535", true},
        {(ConfigValue) () -> "6635", true},
        {(ConfigValue) () -> "65536", false},
        {(ConfigValue) () -> "65.36", false},
        {(ConfigValue) () -> "w2415", false},
        {(ConfigValue) () -> "423415", false},
        {(ConfigValue) () -> "-423415", false},
        {(ConfigValue) () -> "-3415", false},
    };
  }

  @Test(dataProvider = "portPool")
  public void shouldPassWhenValidatorPerformsCorrectValidationOnGivenData(ConfigValue portStringToValidate, boolean expectedValidationResult) {
    assertThat(portConfigValidator.validate(portStringToValidate)).isEqualTo(expectedValidationResult);
  }
}