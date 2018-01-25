package battleships.logging.validation;

import battleships.logging.ConfigValue;
import battleships.logging.ConfigValueName;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.EnumMap;
import java.util.Map;

import static battleships.logging.ConfigValueName.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidatorTest {
  @Test(dataProvider = "mapProvider")
  public void whenValidatorValidatesGivenData_expectResultIsEqualToExpected(Map<ConfigValueName, ConfigValue> testMap, boolean expectedResult) {
    //given
    Validator validator = new Validator();
    //when
    //then
    assertThat(validator.validate(testMap)).isEqualTo(expectedResult);
  }

  @DataProvider(name = "mapProvider")
  public static Object[][] mapProvider() {
    return new Object[][] {
        {createMapForValues("127.0.0.1", "4321", "Max"), true},
        {createMapForValues("127.0.0.s", "4321", "Max"), false},
        {createMapForValues("127.0.0.42", "1221", ""), false},
        {createMapForValues("127.127.125.122", "65222", "Hass"), true},
        {createMapForValues("127", "", ""), false},
        {createMapForValues("127001", "4321", "Trollolo"), false},
        {createMapForValues("-127.-1.-2.1", "4321", "Trollolo"), false},
        {createMapForValues("10.0.0.33", "321", "Iga"), false},
        {createMapForValues("10.0.0.33", "-4321", "Iga"), false},
        {createMapForValues("10.0.0.33.56", "-4321", "Iga"), false},
    };
  }

  private static Map<ConfigValueName, ConfigValue> createMapForValues(String ip, String port, String name) {
    Map<ConfigValueName, ConfigValue> testValuesMap = new EnumMap<>(ConfigValueName.class);
    testValuesMap.put(IP, () -> ip);
    testValuesMap.put(PORT, () -> port);
    testValuesMap.put(NAME, () -> name);
    return testValuesMap;
  }
}