package battleships.communication.jsonhandlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessageableMapperBuilder {

  private ObjectMapper objectMapper;

  public MessageableMapperBuilder withObjectMapper() {
    this.objectMapper = new ObjectMapper();
    return this;
  }

  public MessageableMapper buildWithVisibilityOfNonPrivateAccessors() {
    this.objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    this.objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    return new MessageableMapper(objectMapper);
  }

  public MessageableMapper build() {
    return new MessageableMapper(objectMapper);
  }

}
