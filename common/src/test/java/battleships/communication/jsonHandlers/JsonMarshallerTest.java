package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonMarshallerTest {

    JsonMarshaller jsonMarshaller;

    @BeforeTest
    public void beforeTest(){
        jsonMarshaller = new JsonMarshaller();
    }

    @DataProvider
    public Object[][] messagesPool(){
        return new Object[][]{
                {new WelcomeMessage("hello"), "{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}"},
                {new WelcomeMessage(""), "{\"@type\":\"WelcomeMessage\",\"body\":\"\"}"},
                {new WelcomeMessage(), "{\"@type\":\"WelcomeMessage\",\"body\":\"\"}"},
                {new GoodByeMessage("good bye!"), "{\"@type\":\"GoodByeMessage\",\"body\":\"good bye!\"}"},
                {new GoodByeMessage(""), "{\"@type\":\"GoodByeMessage\",\"body\":\"\"}"},
                {new GoodByeMessage(), "{\"@type\":\"GoodByeMessage\",\"body\":\"\"}"}
        };
    }

    @Test(dataProvider = "messagesPool")
    public void givenObjectOfMessagable_jsonBodyShouldContainsClassNameAndItsFields(Messagable messagable, String expectedJsonString){
        String actualJsonString = jsonMarshaller.convertToJsonString(messagable);
        assertThat(actualJsonString).isEqualTo(expectedJsonString);
    }

}