package battleships.game;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

  private Board board;

  @BeforeMethod
  private void setUp(){
    board = Board.build();
  }

  @Test
  public void whenCheckingCountOfEmptyFields_expectCountIs100() {
    //when
    long countOfEmptyFields = board.getFields().stream().filter(Field::isEmpty).count();
    //then
    assertThat(countOfEmptyFields).isEqualTo(100);
  }

  @Test
  public void whenCountingUnbrokenMasts_expectCountEquals0() {
    //when - then
    assertThat(board.unbrokenMastCount()).isEqualTo(0);
  }

  @Test
  public void whenPlacingTenFieldsWithUnbrokenShipPart_expectedCountOfUnbrokenMastsIs10(){
    //when
    board.getFields().stream()
            .filter(field -> field.getPosition() < 10)
            .forEach(Field::setUnbrokenShipPartOn);
    //then
    assertThat(board.unbrokenMastCount()).isEqualTo(10);
  }

  @Test
  public void whenPlacingFiveFieldsWithUnbrokenShipPart_andFiveFieldsWithBrokenShipPart_expectedCountOfUnbrokenMastsIs5(){
    //when
    board.getFields().stream()
            .filter(field -> field.getPosition() < 5)
            .forEach(Field::setUnbrokenShipPartOn);
    board.getFields().stream()
            .filter(field -> field.getPosition() >= 20 && field.getPosition() < 25)
            .forEach(Field::setBrokenShipPartOn);
    //then
    assertThat(board.unbrokenMastCount()).isEqualTo(5);
  }

  @Test
  public void whenPlacingFiveFieldsWithUnbrokenShipPart_andShootingTwoOfThem_expectedCountOfUnbrokenMastsIs3(){
    //when
    List<Field> fieldsWithUnbrokenShipPart
            = IntStream.range(0, 5)
            .mapToObj(pos -> board.getFields().get(pos))
            .collect(Collectors.toList());
    fieldsWithUnbrokenShipPart.stream().forEach(Field::setUnbrokenShipPartOn);
    fieldsWithUnbrokenShipPart.stream().limit(2).forEach(Field::shoot);
    //then
    assertThat(board.unbrokenMastCount()).isEqualTo(3);
  }

  @Test
  public void whenGettingBoardNodeOfSomeEmptyField_expectedColorOfRectangleBindWithBoardNodeIsAsColorAqua(){
    //when
    Node node = board.rectangleForPosition(0).getStackPane().getChildren().get(0);
    Shape rectangle = (Shape) node;
    //then
    assertThat(rectangle.getFill().equals(Color.AQUA));
  }

  @Test
  public void whenPlacingOnSomeFieldUnbrokenShipPart_expectedColorOfRectangleBindWithBoardNodeIsAsColorYellow(){
    //when
    int someFieldIndex = 0;
    board.getFields().get(someFieldIndex).setUnbrokenShipPartOn();
    Node node = board.rectangleForPosition(someFieldIndex).getStackPane().getChildren().get(0);
    Shape rectangle = (Shape) node;
    //then
    assertThat(rectangle.getFill().equals(Color.YELLOW));
  }
}