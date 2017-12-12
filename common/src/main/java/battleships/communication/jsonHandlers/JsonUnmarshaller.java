package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

class JsonUnmarshaller {

    Messagable convertToMessagable(String message) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(message, Messagable.class);
    }

}
