package battleships.logging;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static battleships.logging.ConfigValueName.IP;
import static battleships.logging.ConfigValueName.NAME;
import static battleships.logging.ConfigValueName.PORT;

public class ConfigMapCreatorTest {
  private SoftAssert softAssert = new SoftAssert();

  @Test(dataProvider = "mapProvider")
  public void whenConfigurationMapIsCreated_expectProperValuesToBeGetFromIt(String ip, String port, String name) {
    //given
    Map<ConfigValueName, ConfigValue> testedMap = ConfigMapCreator.createMap(ip, port, name);
    //then
    softAssert.assertEquals(testedMap.get(IP).stringValue(), ip);
    softAssert.assertEquals(testedMap.get(PORT).stringValue(), port);
    softAssert.assertEquals(testedMap.get(NAME).stringValue(), name);
    softAssert.assertAll();
  }

  @DataProvider(name = "mapProvider")
  public static Object[][] mapProvider() {
    return new Object[][] {
        {"127.0.0.1", "4321", "Max"},
        {"1.1.1.1", "12331", "Franc"},
        {"10.0.0.33", "21421", "Rob"},
        {"4,43,43,1", "5521", "Troll"},
        {"127.233.243.128", "3456", "Hass"},
    };
  }
}