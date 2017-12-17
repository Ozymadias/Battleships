package battleships.communication.jsonHandlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MessagableMapperBuilder {

    private ObjectMapper objectMapper;

    public MessagableMapperBuilder withObjectMapper(){
        this.objectMapper = new ObjectMapper();
        return this;
    }

    public MessagableMapper buildWithVisibilityOfNonPrivateAccessors(){
        this.objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        this.objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        return new MessagableMapper(objectMapper);
    }

    public MessagableMapper build(){
        return new MessagableMapper(objectMapper);
    }

}
