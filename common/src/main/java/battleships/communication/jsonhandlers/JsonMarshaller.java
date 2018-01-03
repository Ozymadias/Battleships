package battleships.communication.jsonhandlers;

import battleships.communication.Marshaller;
import battleships.communication.Messageable;
import battleships.utils.BattleshipUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonMarshaller implements Marshaller {

  private final MessageableMapper messageableMapper;

  public JsonMarshaller(MessageableMapper messageableMapper) {
    this.messageableMapper = messageableMapper;
  }

  @Override
  public String toString(Messageable messageable) {
    try {
      return this.messageableMapper.writeValueAsString(messageable);
    } catch (JsonProcessingException e) {
      return BattleshipUtils.provideEmptyString();
    }
  }
}
