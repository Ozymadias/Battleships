package battleships.gameplay;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SalvoProcessorTest {
    private List<Salvo> salvos;
    private List<Fleet> fleets;

    @BeforeMethod
    public void setUp() {
        salvos = new ArrayList<>();
        fleets = new ArrayList<>();
    }

    @Test(dataProvider = "provider")
    public void shouldPassWhenGivenDataIsProcessedToCorrectResultValue(Quadruplet quadruplet, List expectedResult, int playerNumber) {
        //given
        salvos.add(quadruplet.firstSalvo);
        salvos.add(quadruplet.secondSalvo);
        fleets.add(quadruplet.firstFleet);
        fleets.add(quadruplet.secondFleet);
        //when
        List<SalvoResult> results = new SalvoProcessor().process(salvos, fleets);
        //then
        assertThat(results.get(playerNumber).getResultList()).isEqualTo(expectedResult);
    }

    @DataProvider(name = "provider")
    public static Object[][] provider() {
        return new Object[][]{
                {new Quadruplet(
                        Salvo.createForPositions(1, 2, 3, 4, 5),
                        Salvo.createForPositions(5, 6, 7),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4))),
                        new Fleet(Collections.singletonList(Ship.createShip(1, 2, 3)))),
                        Arrays.asList(1, 2, 3), 0},
                {new Quadruplet(
                        Salvo.createForPositions(59),
                        Salvo.createForPositions(7, 8),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4))),
                        new Fleet(Collections.singletonList(Ship.createShip(1, 2, 3, 4, 5, 59)))),
                        Collections.singletonList(59), 0},
                {new Quadruplet(
                        Salvo.createForPositions(1, 2, 3, 4, 5, 6),
                        Salvo.createForPositions(7, 8),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4))),
                        new Fleet(Collections.singletonList(Ship.createShip(59, 22, 41, 12)))),
                        Collections.emptyList(), 0},
                {new Quadruplet(
                        Salvo.createForPositions(78, 79, 0, 1, 2, 54),
                        Salvo.createForPositions(7, 8),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4))),
                        new Fleet(Collections.singletonList(Ship.createShip(78, 22, 0, 1, 12, 54)))),
                        Arrays.asList(78, 0, 1, 54), 0},
                {new Quadruplet(
                        Salvo.createForPositions(78, 79, 0, 1, 2, 54),
                        Salvo.createForPositions(7, 8),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 5, 4))),
                        new Fleet(Collections.singletonList(Ship.createShip(78, 22, 0, 1, 12, 54)))),
                        Collections.emptyList(), 1},
                {new Quadruplet(
                        Salvo.createForPositions(78, 79, 0, 1, 2, 54),
                        Salvo.createForPositions(3),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 3, 3, 3, 3, 3))),
                        new Fleet(Collections.singletonList(Ship.createShip(6, 54, 3, 5, 7)))),
                        Collections.singletonList(3), 1},
                {new Quadruplet(
                        Salvo.createForPositions(78, 79, 0, 1, 2, 54),
                        Salvo.createForPositions(3, 76, 75, 11, 45, 32, 67, 32, 53, 23),
                        new Fleet(Collections.singletonList(Ship.createShip(3, 7, 6, 78))),
                        new Fleet(Collections.singletonList(Ship.createShip(6, 54, 3, 5, 7)))),
                        Collections.singletonList(3), 1}
        };
    }

    private static class Quadruplet {

        final Salvo firstSalvo;
        final Salvo secondSalvo;
        final Fleet firstFleet;
        final Fleet secondFleet;

        private Quadruplet(Salvo firstSalvo, Salvo SecondSalvo, Fleet firstFleet, Fleet secondFleet) {
            this.firstSalvo = firstSalvo;
            secondSalvo = SecondSalvo;
            this.firstFleet = firstFleet;
            this.secondFleet = secondFleet;
        }
    }
}