package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;

import java.util.HashMap;
import java.util.Map;

public class MessageManagerBuilder {

    JSONMessageManager asJSONManager(){
        JsonMarshaller jsonMarshaller = new JsonMarshaller();

        Map<String, Class<? extends Messagable>> registry = new HashMap<String, Class<? extends Messagable>>();
        registry.put("WelcomeMessage", WelcomeMessage.class);
        registry.put("GoodByeMessage", GoodByeMessage.class);
        JsonUnmarshaller jsonUnmarshaller = new JsonUnmarshaller(registry);

        return new JSONMessageManager(jsonMarshaller, jsonUnmarshaller);
    }

}
