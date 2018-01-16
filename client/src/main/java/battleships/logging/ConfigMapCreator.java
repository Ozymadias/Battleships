package battleships.logging;

import static battleships.logging.ConfigValueName.IP;
import static battleships.logging.ConfigValueName.NAME;
import static battleships.logging.ConfigValueName.PORT;

import java.util.EnumMap;
import java.util.Map;

class ConfigMapCreator {
  private ConfigMapCreator() {
  }

  /**
   * Creates EnumMap of ConfigValueName, Config Value with given data.
   *
   * @param ip   String representation of host IP.
   * @param port String representation of host port.
   * @param name String representation of player name.
   * @return new instance of map with logging configuration
   */
  static Map<ConfigValueName, ConfigValue> createMap(String ip, String port, String name) {
    Map<ConfigValueName, ConfigValue> validationMap = new EnumMap<>(ConfigValueName.class);
    validationMap.put(IP, () -> ip);
    validationMap.put(PORT, () -> port);
    validationMap.put(NAME, () -> name);
    return validationMap;
  }
}
