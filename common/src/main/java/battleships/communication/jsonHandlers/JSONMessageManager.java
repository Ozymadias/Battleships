package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.MessageManager;

import java.io.IOException;
import java.util.Optional;

public class JSONMessageManager implements MessageManager{
    private final JsonMarshaller jsonMarshaller;
    private final JsonUnmarshaller jsonUnmarshaller;

    public JSONMessageManager(JsonMarshaller jsonMarshaller, JsonUnmarshaller jsonUnmarshaller) {
        this.jsonMarshaller = jsonMarshaller;
        this.jsonUnmarshaller = jsonUnmarshaller;
    }

    public String toStringMessage(Messagable messegable){
        String jsonString = jsonMarshaller.convertToJsonString(messegable);
        return jsonString;
    }

    public Optional<Messagable> fromStringMessage(String message){
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
