package battleships.gameplay;

import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public class SinkingShipsTest {

    @Test
    public void testName() {
        List<Fleet> test = new ArrayList<>();
        List<SalvoResult> salvoTest = new ArrayList<>();
        test.add(new Fleet(Arrays.asList(Ship.createShip(3, 5, 4), Ship.createShip(6, 7, 8))));
        test.add(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3), Ship.createShip(6, 7, 8))));
        salvoTest.add(new SalvoResult(Arrays.asList(3, 5, 4,6,7,8), Arrays.asList(3)));
        salvoTest.add(new SalvoResult(Arrays.asList(1, 2, 3), Arrays.asList(66, 54)));

        SinkingShips sinkingShips = new SinkingShips(mock(ArrayList.class), test, salvoTest);
        sinkingShips.process();
    }
}