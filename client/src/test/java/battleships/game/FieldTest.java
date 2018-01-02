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
  public void whenCreatingField_expectItsPositionIsEqualGivenPosition(Integer position) {
    //when
    Field field = new Field(position);
    int fieldPosition = field.getPosition();
    //then
    assertThat(fieldPosition).isEqualTo(position);
  }

  @Test(dataProvider = "positionsPoll")
  public void whenCreatingField_expectItsStateIsEmpty(Integer position) {
    //when
    Field field = new Field(position);
    boolean isFieldEmpty = field.isEmpty();
    //then
    assertThat(isFieldEmpty).isTrue();
  }

  @Test(dataProvider = "positionsPoll")
  public void whenCreatingField_expectItIsNotShot(Integer position) {
    //when
    Field field = new Field(position);
    boolean isFieldShot = field.isShot();
    //then
    assertThat(isFieldShot).isEqualTo(false);
  }

  @Test(dataProvider = "positionsPoll")
  public void whenSettingShipPartOnField_expectFieldStateIsUNBROKEN_SHIP_PART(Integer position) {
    //given
    Field field = new Field(position);
    //when
    field.setUnbrokenShipPartOn();
    boolean isShipOnField = field.isShipOn();
    //then
    assertThat(isShipOnField).isTrue();
  }

}