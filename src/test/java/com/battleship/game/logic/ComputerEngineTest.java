package com.battleship.game.logic;

import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.utils.GeneralUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ComputerEngineTest {

    private final ComputerEngine computerEngine = new ComputerEngine();
    private final GeneralUtils generalUtils = new GeneralUtils();

    @Test
    public void it_tests_computer_engine_column_randomization() throws Exception {
        //given
        Map<String, Integer> availableLettersMap = generalUtils.getAvailableLettersMap();

        //when
        String column = computerEngine.randomizeColumn();

        //then
        boolean containsKey = availableLettersMap.containsKey(column);
        assertTrue(containsKey);
    }

    @Test
    public void it_tests_computer_engine_generation_ship_coords_when_battleship() {
        //given
        Ship ship = new Ship();
        ship.setSizeOfShip(5);

        //when
        computerEngine.generateShipCoords("A", ship);

        //then
        assertNotNull(ship.getCoordinateStartingPosition());
        assertNotNull(ship.getCoordinateFinalPosition());
    }

    @Test
    public void it_tests_computer_engine_generation_ship_coords_when_destroyer() {
        //given
        Ship ship = new Ship();
        ship.setSizeOfShip(4);

        //when
        computerEngine.generateShipCoords("A", ship);

        //then
        assertNotNull(ship.getCoordinateStartingPosition());
        assertNotNull(ship.getCoordinateFinalPosition());
    }

    @Test
    public void it_tests_computer_engine_shot() {
        //given
        Ship ship = new Ship();
        ship.setSizeOfShip(4);

        //when
        Coordinate shot = computerEngine.shot();

        //then
        assertNotNull(shot);
    }
}
