package battleships.communication.jsonhandlers;

import battleships.communication.Marshaller;
import battleships.communication.Messageable;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonMarshallerTest {

  private Marshaller jsonMarshaller;

  @BeforeTest
  public void beforeTest() {
    this.jsonMarshaller = JsonMarshaller.newInstance();
  }

  @DataProvider
  private Object[][] messagesPool() {
    return new Object[][] {
        {new WelcomeMessage("hello"), "{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}"},
        {new WelcomeMessage(""), "{\"@type\":\"WelcomeMessage\",\"body\":\"\"}"},
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