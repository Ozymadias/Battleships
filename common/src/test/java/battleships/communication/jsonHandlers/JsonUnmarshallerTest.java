package battleships.communication.jsonHandlers;

import battleships.communication.Messagable;
import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonUnmarshallerTest {

    private Map<String, Class<? extends Messagable>> registry = new HashMap<>();
    private JsonUnmarshaller jsonUnmarshaller;

    @BeforeTest
    protected void beforeTest(){
        registry.put("WelcomeMessage", WelcomeMessage.class);
        registry.put("GoodByeMessage", GoodByeMessage.class);
        jsonUnmarshaller = new JsonUnmarshaller(registry);
    }

    @DataProvider
    public Object[][] jsonBodyAndRootClassesNamesPoll(){
        return new Object[][] {
                {"{\"WelcomeMessage\":{\"body\":\"hello\"}}", new String[]{"WelcomeMessage"}},
                {"{\"GoodByeMessage\":{\"body\":\"good bye!\"}}", new String[]{"GoodByeMessage"}},
                {"{\"WelcomeMessage\":{\"body\":\"hello\"},\"GoodByMessage\":{\"body\":\"good by!\"}}", new String[]{"WelcomeMessage", "GoodByMessage"}}
        };
    }

    @Test(dataProvider = "jsonBodyAndRootClassesNamesPoll")
    public void givenJsonString_methodGetRootClassName_ShouldReturn_expectedRootsNames(String jsonString, String[] expectedRootsNames) throws IOException {
        List<String> actualRootsNames = jsonUnmarshaller.getRootsNames(jsonString);
        assertThat(actualRootsNames).isEqualTo(Arrays.asList(expectedRootsNames));
    }

    @DataProvider
    public Object[][] jsonBodyAndRootTypesPoll(){
        return new Object[][] {
            {"{\"WelcomeMessage\":{\"body\":\"hello\"}}", WelcomeMessage.class},
            {"{\"GoodByeMessage\":{\"body\":\"good bye!\"}}", GoodByeMessage.class}
        };
    }

    @Test(dataProvider = "jsonBodyAndRootTypesPoll")
    public void givenJsonString_methodReadFromJSONString_shouldReturnReferenceToObjectAsExpected(String jsonString, Class expectedClass) throws IOException, ClassNotFoundException {
        List<Messagable> messagableList = jsonUnmarshaller.convertToMessagableList(jsonString);
        assertThat(messagableList.get(0).getClass()).isEqualTo(expectedClass);
    }
}