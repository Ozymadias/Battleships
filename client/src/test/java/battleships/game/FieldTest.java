package battleships.game;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldTest {

    @Test
    public void givenPosition_whenCreatingField_itsPositionShouldBeEqualAsGiven(){
        //given
        Integer givenPosition = 10;
        //when
        Field field = new Field(givenPosition);
        //then
        assertThat(field.getPosition()).isEqualTo(givenPosition);
    }

    @Test
    public void givenDummyPosition_whenCreatingField_itsStateShouldBeEmpty(){
        //given
        Integer givenDummyPosition = 10;
        //when
        Field field = new Field(givenDummyPosition);
        //then
        assertThat(field.getState()).isEqualTo(FieldState.EMPTY);
    }

    @Test
    public void givenDummyPosition_whenCreatingField_isShotedReturnsFalse(){
        //given
        Integer givenDummyPosition = 10;
        //when
        Field field = new Field(givenDummyPosition);
        //then
        assertThat(field.isShot()).isEqualTo(false);
    }

    @Test
    public void givenSomeField_whenSettingShipPartOnIt_thenFieldStateShouldBe_UNBROKEN_SHIP_PART(){
        //given
        Field field = new Field(10);
        //when
        field.setShipPartOn();
        //then
        assertThat(field.getState()).isEqualTo(FieldState.UNBROKEN_SHIP_PART);
    }

}