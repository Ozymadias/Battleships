package battleships.gameplay;

import org.assertj.core.api.Condition;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newLinkedHashSet;

@Test
public class JediTest {
  
  static Set<String> JEDIS = newLinkedHashSet("Luke", "Yoda", "Obiwan");
  
  Condition<String> jediPower = new Condition<>(JEDIS::contains, "jedi power");
  
  Condition<String> jedi = new Condition<String>("jedi") {
    @Override
    public boolean matches(String value) {
      return JEDIS.contains(value);
    }
  };
  
  public void temp() {
    assertThat("Yoda").is(jedi);
    assertThat("Vader").isNot(jedi);
    try {
      // condition not verified to show the clean error message
      assertThat("Vader").is(jedi);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("expecting:<'Vader'> to be:<jedi>");
    }
  }
  
  public void another() {
    assertThat("Yoda").is(jediPower);
  }
  
}
