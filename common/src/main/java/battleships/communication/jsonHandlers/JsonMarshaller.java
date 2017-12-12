package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonMarshaller {

    private final ObjectMapper objectMapper;

    JsonMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    String convertToJsonString(Messagable messegable){
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(messegable);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonString;
    }

}
