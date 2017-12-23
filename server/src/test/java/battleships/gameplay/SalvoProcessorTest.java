package battleships.gameplay;

import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void testName() {
        salvos.add(Salvo.createForPositions(1, 2, 3));
        salvos.add(Salvo.createForPositions(5, 6, 7));
        fleets.add(new Fleet(Arrays.asList(Ship.createShip(3, 5, 4))));
        fleets.add(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3))));
        List<SalvoResult> results = new SalvoProcessor().process(salvos,fleets);
        assertThat(results.get(1).getResultList()).isEqualTo(Arrays.asList());
    }
}