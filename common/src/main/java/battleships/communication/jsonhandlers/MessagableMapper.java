package battleships.communication.jsonhandlers;

import battleships.communication.Messagable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.IOException;

class MessagableMapper {

  private final ObjectMapper objectMapper;

  MessagableMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  String writeValueAsString(Messagable messagable) throws JsonProcessingException {
    return this.objectMapper.writeValueAsString(messagable);
  }

  Messagable readValue(String message, Class<Messagable> messagableClass) throws IOException {
    return this.objectMapper.readValue(message, messagableClass);
  }

  VisibilityChecker getVisibilityChecker() {
    return this.objectMapper.getVisibilityChecker();
  }
}
