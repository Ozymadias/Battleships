package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONMessageManagerTest {

    JSONMessageManager jsonMessageManager;

    @BeforeTest
    public void beforeTest(){
        jsonMessageManager = new JSONMessageManager(new JsonMarshaller(), new JsonUnmarshaller());
    }

    @DataProvider
    public Object[][] messagesPool(){
        return new Object[][]{
                {"{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}"},
                {"{\"@type\":\"WelcomeMessage\",\"body\":\"\"}"},
                {"{\"@type\":\"WelcomeMessage\",\"body\":\"\"}"},
                {"{\"@type\":\"GoodByeMessage\",\"body\":\"good bye!\"}"},
                {"{\"@type\":\"GoodByeMessage\",\"body\":\"\"}"},
                {"{\"@type\":\"GoodByeMessage\",\"body\":\"\"}"}
        };
    }

    @Test(dataProvider = "messagesPool")
    public void jsonStringConvertedToMessagableAndDecovertedToJsonString_shouldNotChange(String jsonString){
        Messagable messagable = jsonMessageManager.fromStringMessage(jsonString).get();
        String actualJsonString = jsonMessageManager.toStringMessage(messagable);
        assertThat(actualJsonString).isEqualTo(jsonString);

    }

}