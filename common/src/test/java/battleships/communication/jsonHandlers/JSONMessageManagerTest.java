package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.MessageManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JSONMessageManagerTest {

    private MessageManager jsonMessageManager;

    @BeforeTest
    public void beforeTest(){
        jsonMessageManager = JSONMessageManager.build();
    }

    @DataProvider
    private Object[][] messagesPool(){
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
        if(jsonMessageManager.toMessagable(jsonString).isPresent()) {
            Messagable messagable = jsonMessageManager.toMessagable(jsonString).get();
            String actualJsonString = jsonMessageManager.toString(messagable);
            assertThat(actualJsonString).isEqualTo(jsonString);
        }
    }

}