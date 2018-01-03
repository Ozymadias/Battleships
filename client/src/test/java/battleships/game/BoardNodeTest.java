package battleships.game;

import javafx.scene.shape.Rectangle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardNodeTest {

  @DataProvider
  public Object[] positionsPoll() {
    return new Object[] {
        0, 10, 50, 99
    };
  }

  @Test(dataProvider = "positionsPoll")
  public void whenBuildingBoardNodeWithField_expectBoardNodeContainsOneChild(Integer position) {
    //given
    Field field = new Field(position);
    //when
    BoardNode boardNode = BoardNode.build(field);
    //then
    assertThat(boardNode.getStackPane().getChildren().size()).isEqualTo(1);
  }

  @Test(dataProvider = "positionsPoll")
  public void whenBuildingBoardNodeWithField_expectBoardNodeOnlyChildIsOfRectangleClass(Integer position) {
    //given
    Field field = new Field(position);
    //when
    BoardNode boardNode = BoardNode.build(field);
    //then
    assertThat(boardNode.getStackPane().getChildren().get(0).getClass()).isEqualTo(Rectangle.class);
  }

  @Test(dataProvider = "positionsPoll")
  public void whenBuildingBoardNodeWithField_expectBoardNodeIndexEqualsPosition(Integer position) {
    //given
    Field field = new Field(position);
    //when
    BoardNode boardNode = BoardNode.build(field);
    //then
    assertThat(boardNode.getIndex()).isEqualTo(position);
  }

}