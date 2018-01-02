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
  public void whenCheckingIfStringIsNumeric_expectIsNumericOrNot(String toCheck, boolean expectedResult) {
    //when
    boolean isStringNumeric = BattleshipUtils.checkIfStringIsNumeric(toCheck);
    //then
    assertThat(isStringNumeric).isEqualTo(expectedResult);
  }

  @Test(dataProvider = "isEmptyPool")
  public void whenCheckingIfStringIsEmpty_expectItIsEmptyOrNot(String toCheck, boolean expectedResult) {
    //when
    boolean isStringEmpty = BattleshipUtils.checkIfStringIsEmpty(toCheck);
    //then
    assertThat(isStringEmpty).isEqualTo(expectedResult);
  }

  @Test
  public void whenRunningProvideEmptyStringMethod_expectItReturnsEmptyString() {
    //when
    String emptyString = BattleshipUtils.provideEmptyString();
    //then
    assertThat(emptyString).isEqualTo("");
  }
}