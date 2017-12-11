package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.*;

class JsonUnmarshaller {

    private final Map<String, Class<? extends Messagable>> registry;

    public JsonUnmarshaller(Map<String, Class<? extends Messagable>> registry) {
        this.registry = registry;
    }

    List<Messagable> readFromJSONString(String message) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        List<String> rootClassNames  = getRootClassName(message);
        List<Messagable> messegableList = new LinkedList<Messagable>();

        for(String rootClassName : rootClassNames){
                messegableList.add(objectMapper.readValue(message, registry.get(rootClassName)));
        }
        return messegableList;
    }

    List<String> getRootClassName(String message) throws IOException {
        List<String> rootClassesNames = new LinkedList<String>();

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = (ObjectNode) objectMapper.readTree(message);
        Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()){
            rootClassesNames.add(iterator.next());
        }

        return rootClassesNames;
    }

}
