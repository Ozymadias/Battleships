package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Optional;

class JsonMarshaller {

    Optional<String> writeToJsonString(Messagable messegable){
        ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true);

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(messegable);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(jsonString);
    }

}
