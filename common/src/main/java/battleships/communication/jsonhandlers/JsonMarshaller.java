package battleships.communication.jsonhandlers;

import battleships.communication.Marshaller;
import battleships.communication.Messageable;
import battleships.utils.BattleshipUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * This class is a Json implementation of Marshaller. Its main responsibility is to turn a Messageable
 * object into its Json String representation using MessageableMapper class.
 */

public class JsonMarshaller implements Marshaller {

  private final MessageableMapper messageableMapper;

  private JsonMarshaller(MessageableMapper messageableMapper) {
    this.messageableMapper = messageableMapper;
  }

  /**
   * @param messageable - Object of type Messageable to be converted to String
   * @return - Json String representation of the Messageable object.
   */
  @Override
  public String toString(Messageable messageable) {
    try {
      return this.messageableMapper.writeValueAsString(messageable);
    } catch (JsonProcessingException e) {
      return BattleshipUtils.provideEmptyString();
    }
  }

  /**
   * This returns a new instance of JsonMarshaller class.
   */
  public static JsonMarshaller newInstance() {
    return new JsonMarshaller(MessageableMapper.newInstance());
  }
}
