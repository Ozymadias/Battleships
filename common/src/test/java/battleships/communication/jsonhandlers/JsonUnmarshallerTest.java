package battleships.communication.jsonhandlers;

import battleships.communication.Messageable;
import battleships.communication.Unmarshaller;
import battleships.communication.messages.WelcomeMessage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsonUnmarshallerTest {

  private Unmarshaller jsonUnmarshaller;

  @BeforeTest
  protected void beforeTest() {
    this.jsonUnmarshaller = JsonUnmarshaller.newInstance();
  }

  @DataProvider
  private Object[][] jsonBodyAndTypesPoll() {
    return new Object[][] {
        {"{\"@type\":\"WelcomeMessage\",\"body\":\"hello\"}", WelcomeMessage.class}
    };
  }

  @Test(dataProvider = "jsonBodyAndTypesPoll")
  public void whenConvertingToMessageable_expectMessageableShouldHoldReferenceToClassObject(String jsonString, Class expectedClass) throws IOException, ClassNotFoundException {
    //when
    Messageable messageable = jsonUnmarshaller.toMessageable(jsonString).get();
    //then
    assertThat(messageable.getClass()).isEqualTo(expectedClass);
  }
}