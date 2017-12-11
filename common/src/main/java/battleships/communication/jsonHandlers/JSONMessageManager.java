package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.MessageManager;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class JSONMessageManager implements MessageManager {

    private final JsonMarshaller jsonMarshaller;
    private final JsonUnmarshaller jsonUnmarshaller;

    public JSONMessageManager(JsonMarshaller jsonMarshaller, JsonUnmarshaller jsonUnmarshaller) {
        this.jsonMarshaller = jsonMarshaller;
        this.jsonUnmarshaller = jsonUnmarshaller;
    }

    public String toMessage(Messagable messegable){
        Optional<String> opt = jsonMarshaller.writeToJsonString(messegable);
        return opt.toString();
    }

    public List<Messagable> fromMessage(String message){
        List<Messagable> messagables = new LinkedList<>();

        try {
            messagables.addAll(jsonUnmarshaller.readFromJSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return messagables;
    }
}
