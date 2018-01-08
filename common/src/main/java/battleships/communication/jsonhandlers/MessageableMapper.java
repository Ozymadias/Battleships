package battleships.communication.jsonhandlers;

import battleships.communication.Messageable;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.IOException;

class MessageableMapper {

  private final ObjectMapper objectMapper;

  private MessageableMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  String writeValueAsString(Messageable messageable) throws JsonProcessingException {
    return this.objectMapper.writeValueAsString(messageable);
  }

  Messageable readValue(String message, Class<Messageable> messageableClass) throws IOException {
    return this.objectMapper.readValue(message, messageableClass);
  }

  static MessageableMapper newInstance() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    return new MessageableMapper(objectMapper);
  }
}
