package battleships.communication;

import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.*;

public class JsonUnmarshaller {

    private Map<String, Class<? extends Messegable>> registry = new HashMap<String, Class<? extends Messegable>>();

    //TODO: poprawić! Klasa zależy od poszczególnych implementacji.
    public void init(){
        registry.put("WelcomeMessage", WelcomeMessage.class);
        registry.put("GoodByeMessage", GoodByeMessage.class);
    }

    public List<Messegable> readFromJSONString(String message) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        List<String> rootClassNames  = getRootClassName(message);
        List<Messegable> messegableList = new LinkedList<Messegable>();

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
