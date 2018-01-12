package battleships.logging;

import java.util.EnumMap;
import java.util.Map;

import static battleships.logging.ConfigValueName.IP;
import static battleships.logging.ConfigValueName.NAME;
import static battleships.logging.ConfigValueName.PORT;

class ConfigMapCreator {
  private ConfigMapCreator() {
  }

  /**
   * Creates EnumMap of ConfigValueName, Config Value with given data.
   *
   * @param ip   String representation of host IP.
   * @param port String representation of host port.
   * @param name String representation of player name.
   * @return returns new instance of <ConfigValueName, ConfigValue> map
   * filled with given data*/
  static Map<ConfigValueName, ConfigValue> createMap(String ip, String port, String name) {
    Map<ConfigValueName, ConfigValue> validationMap = new EnumMap<>(ConfigValueName.class);
    validationMap.put(IP, () -> ip);
    validationMap.put(PORT, () -> port);
    validationMap.put(NAME, () -> name);
    return validationMap;
  }
}
