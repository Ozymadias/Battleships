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
    Field field = new Field(position);
    assertThat(field.getPosition()).isEqualTo(position);
  }

  @Test(dataProvider = "positionsPoll")
  public void whenCreatingField_expectItsStateIsEmpty(Integer position) {
    Field field = new Field(position);
    assertThat(field.isEmpty()).isTrue();
  }

  @Test(dataProvider = "positionsPoll")
  public void whenCreatingField_expectItIsNotShot(Integer position) {
    Field field = new Field(position);
    assertThat(field.isShot()).isEqualTo(false);
  }

  @Test(dataProvider = "positionsPoll")
  public void whenSettingShipPartOnField_expectFieldStateIsUNBROKEN_SHIP_PART(Integer position) {
    Field field = new Field(position);
    field.setUnbrokenShipPartOn();
    assertThat(field.isShipOn()).isTrue();
  }

}