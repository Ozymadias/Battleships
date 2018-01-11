package battleships.communication.jsonhandlers;

import battleships.communication.Marshaller;
import battleships.utils.BattleshipUtils;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class is a Json implementation of Marshaller. Its main responsibility is to turn a Messageable
 * object into its Json String representation using MessageableMapper class.
 */

public class JsonMarshaller implements Marshaller {

  private final ObjectMapper objectMapper;

  private JsonMarshaller(ObjectMapper messageableMapper) {
    this.objectMapper = messageableMapper;
  }

  /**
   * @param value - object to be converted to Json String
   * @return - Json String representation of the Messageable object.
   */
  public <T> String writeValueAsString(T value) {
    try {
      return this.objectMapper.writeValueAsString(value);
    } catch (JsonProcessingException e) {
      return BattleshipUtils.provideEmptyString();
    }
  }

  /**
   * This returns a new instance of JsonMarshaller class.
   */
  public static JsonMarshaller newInstance() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    return new JsonMarshaller(objectMapper);
  }
}
