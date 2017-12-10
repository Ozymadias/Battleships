package battleships.communication;

import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class JsonUnmarshallerTest {


    @Test
    public void getRootClassNameTest() throws IOException {
        String jsonString = "{\"WelcomeMessage\":{\"body\":\"hello\"}}";
        JsonUnmarshaller jsonUnmarshaller = new JsonUnmarshaller();
        List<String> rootsNames = jsonUnmarshaller.getRootClassName(jsonString);
        assertEquals(rootsNames, Arrays.asList(new String[]{"WelcomeMessage"}));
    }

    @Test
    public void getRootClassNameTest2() throws IOException {
        String jsonString = "{\"WelcomeMessage\":{\"body\":\"hello\"}," +
                "\"GoodByMessage\":{\"body\":\"good by!\"}}";
        JsonUnmarshaller jsonUnmarshaller = new JsonUnmarshaller();
        List<String> rootsNames = jsonUnmarshaller.getRootClassName(jsonString);
        assertEquals(rootsNames, Arrays.asList(new String[]{"WelcomeMessage", "GoodByMessage"}));
    }

    @Test
    public void readFromJSONStringTest() throws IOException, ClassNotFoundException {
        String jsonString = "{\"WelcomeMessage\":{\"body\":\"hello\"}}";
        JsonUnmarshaller jsonUnmarshaller = new JsonUnmarshaller();
        jsonUnmarshaller.init();
        List<Messegable> messegableList = jsonUnmarshaller.readFromJSONString(jsonString);
        assertEquals(messegableList.size(), 1);
        assertEquals(messegableList.get(0) instanceof WelcomeMessage, true);
    }

}