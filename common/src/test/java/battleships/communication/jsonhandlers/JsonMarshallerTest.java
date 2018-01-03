package battleships.communication.jsonhandlers;

import battleships.communication.Marshaller;
import battleships.communication.Messageable;
import battleships.communication.messages.GoodByeMessage;
import battleships.communication.messages.WelcomeMessage;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonMarshallerTest {

  private Marshaller jsonMarshaller;

  @BeforeTest
  public void beforeTest() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NON_PRIVATE);
    MessageableMapper messageableMapper = new MessageableMapper(objectMapper);
    this.jsonMarshaller = new JsonMarshaller(messageableMapper);
  }

  @DataProvider
  private Object[][] messagesPool() {
    return new Object[][] {
        {new WelcomeMessage("hello"), "{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}"},
        {new WelcomeMessage(""), "{\"@type\":\"WelcomeMessage\",\"body\":\"\"}"},
        {new GoodByeMessage("good bye!"), "{\"@type\":\"GoodByeMessage\",\"body\":\"good bye!\"}"},
        {new GoodByeMessage(""), "{\"@type\":\"GoodByeMessage\",\"body\":\"\"}"}
    };
  }

  @Test(dataProvider = "messagesPool")
  public void whenConvertingToJsonString_expectJsonBodyContainsClassNameAndItsFields(Messageable messageable, String expectedJsonString) {
    //when
    String actualJsonString = jsonMarshaller.toString(messageable);
    //then
    assertThat(actualJsonString).isEqualTo(expectedJsonString);
  }

}