package battleships.communication.jsonHandlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

class MessagableMapperBuilder {

    private ObjectMapper objectMapper;

    MessagableMapperBuilder withObjectMapper(){
        this.objectMapper = new ObjectMapper();
        return this;
    }

    MessagableMapper buildWithVisibilityOfNonPrivateAccessors(){
        this.objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        this.objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        return new MessagableMapper(objectMapper);
    }

    MessagableMapper build(){
        return new MessagableMapper(objectMapper);
    }

}
