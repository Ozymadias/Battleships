package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonUnmarshallerTest {

    private JsonUnmarshaller jsonUnmarshaller;

    @BeforeTest
    protected void beforeTest(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
        this.jsonUnmarshaller = new JsonUnmarshaller(objectMapper);
    }

    @DataProvider
    private Object[][] jsonBodyAndTypesPoll(){
        return new Object[][] {
                {"{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}", WelcomeMessage.class},
                {"{\"@type\":\"GoodByeMessage\",\"body\":\"good bye\"}", GoodByeMessage.class}
        };
    }

    @Test(dataProvider = "jsonBodyAndTypesPoll")
    public void givenJsonString_whenConvertingToMessagable_thenMessagableShouldHoldReferenceToClassObjectAsExpected(String jsonString, Class expectedClass) throws IOException, ClassNotFoundException {
        Messagable messagable = jsonUnmarshaller.convertToMessagable(jsonString);
        assertThat(messagable.getClass()).isEqualTo(expectedClass);
    }
}