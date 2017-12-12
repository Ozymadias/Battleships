package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.MessageManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class JSONMessageManager implements MessageManager{
    private final JsonMarshaller jsonMarshaller;
    private final JsonUnmarshaller jsonUnmarshaller;

    public static JSONMessageManager build(){
        return new JSONMessageManager(new JsonMarshaller(new ObjectMapper()), new JsonUnmarshaller(new ObjectMapper()));
    }

    private JSONMessageManager(JsonMarshaller jsonMarshaller, JsonUnmarshaller jsonUnmarshaller) {
        this.jsonMarshaller = jsonMarshaller;
        this.jsonUnmarshaller = jsonUnmarshaller;
    }

    public String toString(Messagable messegable){
        String jsonString = jsonMarshaller.convertToJsonString(messegable);
        return jsonString;
    }

    public Optional<Messagable> toMessagable(String message){
        Messagable messagable = null;
        try {
            messagable = jsonUnmarshaller.convertToMessagable(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(messagable);
    }
}
