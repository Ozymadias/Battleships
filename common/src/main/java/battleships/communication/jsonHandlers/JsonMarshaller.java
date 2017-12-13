package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.utils.BattleshipUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonMarshaller {

    private final ObjectMapper objectMapper;

    JsonMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    String convertToJsonString(Messagable messegable){
        try {
            return objectMapper.writeValueAsString(messegable);
        } catch (JsonProcessingException e) {
            return BattleshipUtils.provideEmptyString();
        }
    }

}
