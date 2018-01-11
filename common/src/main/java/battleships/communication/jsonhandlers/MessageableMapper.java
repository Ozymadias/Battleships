package battleships.communication.jsonhandlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class MessageableMapper {

  private final ObjectMapper objectMapper;

  private MessageableMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  <T extends Object> String writeValueAsString(T value) throws JsonProcessingException {
    return this.objectMapper.writeValueAsString(value);
  }

  <T> T readValue(String message, Class<T> valueClass) throws IOException {
    return this.objectMapper.readValue(message, valueClass);
  }

  static MessageableMapper newInstance() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    return new MessageableMapper(objectMapper);
  }
}
