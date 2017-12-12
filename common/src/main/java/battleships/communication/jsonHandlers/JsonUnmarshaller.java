package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class JsonUnmarshaller {

    private final ObjectMapper objectMapper;

    JsonUnmarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    Messagable convertToMessagable(String message) throws IOException, ClassNotFoundException {
        return objectMapper.readValue(message, Messagable.class);
    }

}
