package battleships.utils;

import battleships.ships.Fleet;
import battleships.ships.Ship;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static battleships.ships.Ship.*;

public class FleetConverterTest {
    private FleetConverter fleetConverter;

    @BeforeTest
    private void setUp() {
        fleetConverter = new FleetConverter();
    }

    @DataProvider(name = "shipInPositionList")
    private static Object[][] shipInPositionList() {

        return new Object[][]
                {
                        {createShip(1,2,3,4),1},
                        {createShip(1,2,3,4),2},
                        {createShip(1,2,3,4),3},
                        {createShip(1,2,3,4),4},
                };
    }

    @Test(dataProvider = "shipInPositionList")
    void givenShipOnPositionsWhenConvertFleetToMapThenMapHasShipOnPosition(Ship ship,int position) {
          List<Ship> ships = new ArrayList<>();
          ships.add(ship);
          Fleet fleet = new Fleet(ships);
          Map<Integer, Ship> testMap = fleetConverter.convert(fleet);
          assertThat(testMap.get(position)).isEqualTo(ship);

    }

    @DataProvider(name = "shipNotInPositionList")
    private static Object[][] shipNotInPositionList() {

        return new Object[][]
                {
                        {createShip(1,2,3,4),0},
                        {createShip(1,2,3,4),5}
                };
    }

    @Test(dataProvider = "shipNotInPositionList")
    void givenShipOnPositionsWhenConvertFleetToMapThenMapHasNotShipOnPosition(Ship ship,int position) {
        List<Ship> ships = new ArrayList<>();
        ships.add(ship);
        Fleet fleet = new Fleet(ships);
        Map<Integer, Ship> testMap = fleetConverter.convert(fleet);
        assertThat(testMap.get(position)).isNull();
    }

    @DataProvider(name = "shipsInPositionsList")
    private static Object[][] shipsInPositionsList() {

        return new Object[][]
                {
                        {createShip(1,2,3,4),createShip(99,98,97,96),1,99},
                        {createShip(1,2,3,4),createShip(99,98,97,96),2,98},
                        {createShip(1,2,3,4),createShip(99,98,97,96),3,97},
                        {createShip(1,2,3,4),createShip(99,98,97,96),4,96}
                };
    }

    @Test(dataProvider = "shipsInPositionsList")
    void givenShipsOnPositionsWhenConvertFleetToMapThenMapHasShipsOnPositions(Ship ship1, Ship ship2, int position1, int position2) {
        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);
        Fleet fleet = new Fleet(ships);
        Map<Integer, Ship> testMap = fleetConverter.convert(fleet);
        assertThat(testMap.get(position1)).isEqualTo(ship1);
        assertThat(testMap.get(position2)).isEqualTo(ship2);
    }

    @DataProvider(name = "shipsNotInPositionsList")
    private static Object[][] shipsNotInPositionsList() {

        return new Object[][]
                {
                        {createShip(1,2,3,4),createShip(99,98,97,96),0,100},
                        {createShip(1,2,3,4),createShip(99,98,97,96),5,95},
                };
    }

    @Test(dataProvider = "shipsNotInPositionsList")
    void givenShipsOnPositionsWhenConvertFleetToMapThenMapHasNotShipsOnPositions(Ship ship1, Ship ship2, int position1, int position2) {
        List<Ship> ships = new ArrayList<>();
        ships.add(ship1);
        ships.add(ship2);
        Fleet fleet = new Fleet(ships);
        Map<Integer, Ship> testMap = fleetConverter.convert(fleet);
        assertThat(testMap.get(position1)).isNull();
        assertThat(testMap.get(position2)).isNull();
    }

}