package battleships.gameplay;

import org.testng.annotations.DataProvider;

/**
 * @author LAFK_pl, Tomasz.Borek@gmail.com
 */
public class TestDPFleetsAndSalvos {

  @DataProvider
  public static Object[][] fleetAndSalvosData() {
    return Triplet.provideTestDataForSinkingShips();
  }

}
