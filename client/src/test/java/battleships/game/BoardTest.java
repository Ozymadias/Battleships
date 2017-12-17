package battleships.game;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BoardTest {

    @Test
    public void buildExampleTest(){
        Board board = Board.buildExample();
        System.out.println(board.fields);
    }

}