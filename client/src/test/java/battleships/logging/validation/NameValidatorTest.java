package battleships.logging.validation;

import battleships.logging.ConfigValue;
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

    return new Object[][] {
        {(ConfigValue) () -> "wololo", true},
        {(ConfigValue) () -> "Iga", true},
        {(ConfigValue) () -> "Krzysztof", true},
        {(ConfigValue) () -> "", false},
        {(ConfigValue) () -> "Blob zabojca z kosmosu", true},
    };
  }

  @Test(dataProvider = "namePool")
  public void shouldPassWhenValidationOfNameThatIsOtherThanNullIsCorrect(ConfigValue stringToValidate, boolean expectedResult) {
    assertThat(nameValidator.validate(stringToValidate)).isEqualTo(expectedResult);
  }
}