package com.battleship.game.logic;

import com.battleship.game.enums.CoordType;
import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    public void it_tests_is_coordinate_correct_length(){
        //given
        Ship ship = new Ship();
        String[] coordinate = new String[2];
        coordinate[0] = "A";
        coordinate[1] = "8";
        CoordType type = CoordType.START;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertTrue(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_coordinate_correct_3_length(){
        //given
        Ship ship = new Ship();


        String[] coordinate = new String[3];
        coordinate[0] = "A";
        coordinate[1] = "1";
        coordinate[2] = "0";

        CoordType type = CoordType.START;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertTrue(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_coordinate_not_correct_3_length(){
        //given
        Ship ship = new Ship();


        String[] coordinate = new String[3];
        coordinate[0] = "A";
        coordinate[1] = "1";
        coordinate[2] = "A";

        CoordType type = CoordType.START;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertFalse(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_coordinate_not_correct_length(){
        //given
        Ship ship = new Ship();


        String[] coordinate = new String[4];
        coordinate[0] = "A";
        coordinate[1] = "1";
        coordinate[2] = "0";
        coordinate[3] = "1";

        CoordType type = CoordType.START;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertFalse(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_coordinate_not_correct(){
        //given
        Ship ship = new Ship();


        String[] coordinate = new String[2];
        coordinate[0] = "W";
        coordinate[1] = "8";

        CoordType type = CoordType.START;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertFalse(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_final_coordinate_not_correct_slant_coordinate(){
        //given
        Ship ship = new Ship();
        Coordinate coordinateA = new Coordinate();
        coordinateA.setCol("A");
        coordinateA.setRow(1);

        ship.setCoordinateStartingPosition(coordinateA);

        String[] coordinate = new String[2];
        coordinate[0] = "B";
        coordinate[1] = "2";

        CoordType type = CoordType.END;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertFalse(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_final_coordinate_not_correct(){
        //given
        Ship ship = new Ship();
        Coordinate coordinateA = new Coordinate();
        coordinateA.setCol("A");
        coordinateA.setRow(1);

        ship.setCoordinateStartingPosition(coordinateA);

        String[] coordinate = new String[2];
        coordinate[0] = "B";
        coordinate[1] = "B";

        CoordType type = CoordType.END;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertFalse(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_final_coordinate_not_correct_not_eq_ship_len(){
        //given
        Ship ship = new Ship();
        ship.setSizeOfShip(4);

        Coordinate coordinateA = new Coordinate();
        coordinateA.setCol("A");
        coordinateA.setRow(1);

        ship.setCoordinateStartingPosition(coordinateA);

        String[] coordinate = new String[2];
        coordinate[0] = "A";
        coordinate[1] = "5";

        CoordType type = CoordType.END;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertFalse(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_final_coordinate_correct_eq_ship_len(){
        //given
        Ship ship = new Ship();
        ship.setSizeOfShip(4);

        Coordinate coordinateA = new Coordinate();
        coordinateA.setCol("A");
        coordinateA.setRow(1);

        ship.setCoordinateStartingPosition(coordinateA);

        String[] coordinate = new String[2];
        coordinate[0] = "D";
        coordinate[1] = "1";

        CoordType type = CoordType.END;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertTrue(isCoordinateCorrect);
    }

    @Test
    public void it_tests_is_final_coordinate_starting_point_bigger_correctly_pass(){
        //given
        Ship ship = new Ship();
        ship.setSizeOfShip(4);

        Coordinate coordinateA = new Coordinate();
        coordinateA.setCol("A");
        coordinateA.setRow(8);

        ship.setCoordinateStartingPosition(coordinateA);

        String[] coordinate = new String[2];
        coordinate[0] = "A";
        coordinate[1] = "5";

        CoordType type = CoordType.END;

        //when
        boolean isCoordinateCorrect = validator.validateCoordinateValue(coordinate, ship, type);

        //then
        assertTrue(isCoordinateCorrect);
    }
}
