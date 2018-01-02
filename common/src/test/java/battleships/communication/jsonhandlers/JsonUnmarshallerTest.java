package battleships.communication.jsonhandlers;

import battleships.communication.Messagable;
import battleships.communication.Unmarshaller;
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

  private Unmarshaller jsonUnmarshaller;

  @BeforeTest
  protected void beforeTest() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    MessagableMapper messagableMapper = new MessagableMapper(objectMapper);
    this.jsonUnmarshaller = new JsonUnmarshaller(messagableMapper);
  }

  @DataProvider
  private Object[][] jsonBodyAndTypesPoll() {
    return new Object[][] {
        {"{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}", WelcomeMessage.class},
        {"{\"@type\":\"GoodByeMessage\",\"body\":\"good bye\"}", GoodByeMessage.class}
    };
  }

  @Test(dataProvider = "jsonBodyAndTypesPoll")
  public void whenConvertingToMessagable_expectMessagableShouldHoldReferenceToClassObject(String jsonString, Class expectedClass) throws IOException, ClassNotFoundException {
    //when
    Messagable messagable = jsonUnmarshaller.toMessagable(jsonString).get();
    //then
    assertThat(messagable.getClass()).isEqualTo(expectedClass);
  }
}