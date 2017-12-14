package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.MessageManager;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class JSONMessageManager implements MessageManager{
    private final JsonMarshaller jsonMarshaller;
    private final JsonUnmarshaller jsonUnmarshaller;

    public static JSONMessageManager build(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        return new JSONMessageManager(new JsonMarshaller(objectMapper), new JsonUnmarshaller(new ObjectMapper()));
    }

    private JSONMessageManager(final JsonMarshaller jsonMarshaller, final JsonUnmarshaller jsonUnmarshaller) {
        this.jsonMarshaller = jsonMarshaller;
        this.jsonUnmarshaller = jsonUnmarshaller;
    }

    public String toString(Messagable messegable){
        return jsonMarshaller.convertToJsonString(messegable);
    }

    public Optional<Messagable> toMessagable(String message){
        Messagable messagable = null;
        try {
            messagable = jsonUnmarshaller.convertToMessagable(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(messagable);
    }
}
