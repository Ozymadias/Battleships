package battleships.communication.jsonhandlers;

import battleships.communication.Unmarshaller;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonUnmarshallerTest {

  private Unmarshaller jsonUnmarshaller;

  @BeforeTest
  protected void beforeTest() {
    this.jsonUnmarshaller = JsonUnmarshaller.newInstance();
  }

  @Test
  public void whenConvertingJsonStringToConcreteObject_expectObjectToBeAppropriate() throws IOException, ClassNotFoundException {
    //given
    String jsonBody = "{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}";
    //when
    WelcomeMessage welcomeMessage = jsonUnmarshaller.readValue(jsonBody, WelcomeMessage.class).get();
    //then
    assertThat(welcomeMessage.getBody()).isEqualTo("hello");
  }

  @Test(expectedExceptions = java.util.NoSuchElementException.class)
  public void whenConvertingInvalidJsonStringToConcreteObject_expectNoSuchElementExceptionToBeThrown() throws IOException, ClassNotFoundException {
    //given
    String jsonBody = "{dummyContent}";
    //when
    WelcomeMessage welcomeMessage = jsonUnmarshaller.readValue(jsonBody, WelcomeMessage.class).get();
    //then
    assertThat(welcomeMessage.getBody()).isEqualTo("hello");
  }

  @Test(expectedExceptions = java.util.NoSuchElementException.class)
  public void whenConvertingJsonStringWithWrongTypeToConcreteObject_expectNoSuchElementExceptionToBeThrown() throws IOException, ClassNotFoundException {
    //given
    String jsonBody = "{\"@type\":\"SomeClassName\",\"body\":\"hello\"}";
    //when
    WelcomeMessage welcomeMessage = jsonUnmarshaller.readValue(jsonBody, WelcomeMessage.class).get();
    //then
    assertThat(welcomeMessage.getBody()).isEqualTo("hello");
  }
}