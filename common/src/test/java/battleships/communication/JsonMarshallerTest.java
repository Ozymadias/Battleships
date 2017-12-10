package battleships.communication;

import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class JsonMarshallerTest {

    @Test
    public void testMappingWelcomeMessageToJson(){
        WelcomeMessage welcomeMessage = new WelcomeMessage("hello");
        JsonMarshaller jsonMarshaller = new JsonMarshaller();
        String jsonString = jsonMarshaller.writeToJsonString(welcomeMessage);
        assertEquals(jsonString, "{\"WelcomeMessage\":{\"body\":\"hello\"}}");
    }

    @Test
    public void testMappingGoodByeMessageToJson(){
        GoodByeMessage goodByeMessage = new GoodByeMessage("good bye!");
        JsonMarshaller jsonMarshaller = new JsonMarshaller();
        String jsonString = jsonMarshaller.writeToJsonString(goodByeMessage);
        assertEquals(jsonString, "{\"GoodByeMessage\":{\"body\":\"good bye!\"}}");
    }

}