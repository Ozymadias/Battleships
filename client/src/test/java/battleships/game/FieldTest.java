package battleships.game;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldTest {

  @DataProvider
  private Object[] positionsPoll() {
    return new Random().ints(10, 0, 99).boxed().toArray();
  }


  @Test(dataProvider = "positionsPoll")
  public void givenSomePosition_whenCreatingField_itsPositionShouldBeEqualAsGiven(Integer position) {
    //when
    Field field = new Field(position);
    //then
    assertThat(field.getPosition()).isEqualTo(position);
  }

  @Test(dataProvider = "positionsPoll")
  public void givenSomePosition_whenCreatingField_itsStateShouldBeEmpty(Integer position) {
    //when
    Field field = new Field(position);
    //then
    assertThat(field.isEmpty()).isTrue();
  }

  @Test(dataProvider = "positionsPoll")
  public void givenSomePosition_whenCreatingField_isShotReturnsFalse(Integer position) {
    //when
    Field field = new Field(position);
    //then
    assertThat(field.isShot()).isEqualTo(false);
  }

  @Test(dataProvider = "positionsPoll")
  public void givenSomeField_whenSettingShipPartOnIt_thenFieldStateShouldBe_UNBROKEN_SHIP_PART(Integer position) {
    //given
    Field field = new Field(position);
    //when
    field.setUnbrokenShipPartOn();
    //then
    assertThat(field.isShipOn()).isTrue();
  }

}