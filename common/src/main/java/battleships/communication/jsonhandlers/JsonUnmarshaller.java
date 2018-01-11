package battleships.communication.jsonhandlers;

import battleships.communication.MessageSender;
import battleships.communication.Messageable;
import battleships.communication.Unmarshaller;
import battleships.logger.BattleshipLog;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

/**
 * This class is a Json implementation of Unmarshaller. Its main responsibility is to turn a Json String
 * object into its Messageable representation using MessageableMapper class.
 */
public class JsonUnmarshaller implements Unmarshaller {
  private final BattleshipLog log = BattleshipLog.provideLogger(MessageSender.class);
  private final ObjectMapper objectMapper;

  private JsonUnmarshaller(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  /**
   * @param message - Json String to be converted to object
   * @return Optional of object that is representation of the Json String object.
    Optional can be empty if IO exception is thrown.
   */
  @Override
  public <T> Optional<T> readValue(String message, Class<T> valueClass) {
    try {
      return Optional.of(this.objectMapper.readValue(message, valueClass));
    } catch (IOException e) {
      log.error(e.getMessage());
      return Optional.empty();
    }
  }

  /**
   * This returns a new instance of JsonUnmarshaller.
   */
  public static JsonUnmarshaller newInstance() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    return new JsonUnmarshaller(objectMapper);
  }
}
