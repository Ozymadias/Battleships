package battleships.gameplay.util;

import org.testng.annotations.DataProvider;

public class TripletDP {
  @DataProvider(name = "fleetAndSalvosData")
  public static Object[][] fleetAndSalvosData() {
    return Triplet.provideTestDataForSinkingShips();
  }
}
