package battleships;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PortValidatorTest {

  private PortValidator portValidator = new PortValidator();

  @DataProvider
  private Object[] incorrectPortsPool(){
    return new Object[] {"aaa", "\n", "0", "-1", "1024", "75535", "1000000"};
  }

  @DataProvider
  private Object[] correctPortsPoll(){
    return new Object[] {"1025", "8000", "8800", "65535"};
  }

  @Test(dataProvider = "incorrectPortsPool")
  public void whenValidatingIncorrectPort_expectValidateMethodToReturnFalse(String port){
    assertThat(portValidator.validate(port)).isFalse();
  }

  @Test(dataProvider = "correctPortsPoll")
  public void whenValidatingCorrectPort_expectValidateMethodToReturnTrue(String port){
    assertThat(portValidator.validate(port)).isTrue();
  }

}