package battleships.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BattleshipUtilsTest {


  @DataProvider(name = "isNumericPool")
  private static Object[][] isNumericPool() {

    return new Object[][] {
        {"wololo", false},
        {"0.0.0.0", false},
        {" 0", false},
        {"255      ", false},
        {"-1", false},
        {"1033", true},
        {"000000004", true},
        {"-10.0.0.3453", false},
        {"30r432", false},
        {"r432", false},
    };
  }

  @DataProvider(name = "isEmptyPool")
  private static Object[][] isEmptyPool() {

    return new Object[][] {
        {"wololo", false},
        {"0", false},
        {" 0", false},
        {"", true},
        {"-1", false},
        {"1033", false},
        {"\n", false},
        {null, true},
        {"30r432", false},
        {"r432", false},
    };
  }

  @Test(dataProvider = "isNumericPool")
  public void shouldPassWhenGivenStringIsCorrectlyEvaluatedThatItContainsOnlyNumbers(String toCheck, boolean expectedResult) {
    assertThat(BattleshipUtils.checkIfStringIsNumeric(toCheck)).isEqualTo(expectedResult);
  }

  @Test(dataProvider = "isEmptyPool")
  public void shouldPassWhenStringIsCorrectlyEvaluatedThatItIsNotEmptyOrNull(String toCheck, boolean expectedResult) {
    assertThat(BattleshipUtils.checkIfStringIsEmpty(toCheck)).isEqualTo(expectedResult);
  }

  @Test
  public void checkIfMethodReturnCorrectValueOfEmptyString() {
    assertThat(BattleshipUtils.provideEmptyString()).isEqualTo("");
  }
}