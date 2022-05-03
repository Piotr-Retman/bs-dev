package com.battleship.game.logic;

import com.battleship.game.enums.CoordType;
import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.utils.GeneralUtils;
import org.springframework.shell.standard.ShellComponent;

import java.util.Map;

@ShellComponent
public class Validator {

    private final String ERROR_SHOULD_HAVE_TWO_VALUES = "ERROR: Coordinate should have two values - column and row like: A5!";
    private final String ERROR_PARSE = "ERROR: Wrongly parsed value!";
    private final String ERROR_COORDINATE = "ERROR: Wrong coordinate letter used!";
    private final String ERROR_COORDINATE_SLANT = "ERROR: Ship should be placed vertically or horizontally!";
    private final String ERROR_SHIP_LEN = "ERROR: Based on ship type it should have length 5 for Battleship or 4 for Destroyer!";

    public boolean validateCoordinateValue(String[] coordinate, Ship playerShip, CoordType type) {
        return isCoordLenValValid(coordinate) &&
                isCoordLen3ValueValid(coordinate) &&
                isCoordColumnValid(coordinate) &&
                isShipCoordsValid(coordinate, playerShip, type);
    }

    private boolean isShipCoordsValid(String[] coordinate, Ship playerShip, CoordType type) {
        GeneralUtils generalUtils = new GeneralUtils();
        if (type == CoordType.END) {
            try {
                Coordinate coordinateStartingPosition = playerShip.getCoordinateStartingPosition();
                String col = coordinateStartingPosition.getCol();
                String columnCurrPoint = coordinate[0];
                int sizeOfShip = playerShip.getSizeOfShip();

                int endRow = generalUtils.countRow(coordinate);
                int row = coordinateStartingPosition.getRow();

                int diff = 1;

                if (col.equals(columnCurrPoint)) {
                    if (endRow > row) {
                        diff = diff + endRow - row;
                    } else {
                        diff = diff + row - endRow;
                    }
                } else if (endRow == row) {
                    int i = Math.abs(countShipLenBasedOnColumn(col, columnCurrPoint));
                    diff = diff + i;
                } else {
                    System.out.println(ERROR_COORDINATE_SLANT);
                    return false;
                }

                if (sizeOfShip != Math.abs(diff)) {
                    System.out.println(ERROR_SHIP_LEN);
                    return false;
                }

            } catch (NumberFormatException ex) {
                System.out.println(ERROR_PARSE);
                return false;
            }
        }
        return true;
    }

    private int countShipLenBasedOnColumn(String firstCol, String secondCol) {
        GeneralUtils generalUtils = new GeneralUtils();
        Map<String, Integer> availableLetters = generalUtils.getAvailableLettersMap();
        int coordA = availableLetters.get(firstCol);
        int coordB = availableLetters.get(secondCol);
        return coordA - coordB;
    }

    private boolean isCoordColumnValid(String[] coordinate) {
        GeneralUtils generalUtils = new GeneralUtils();
        Map<String, Integer> availableLetters = generalUtils.getAvailableLettersMap();
        if (!availableLetters.containsKey(coordinate[0])) {
            System.out.println(ERROR_COORDINATE);
            return false;
        }
        return true;
    }

    private boolean isCoordLen3ValueValid(String[] coordinate) {
        if (coordinate.length == 3) {
            try {
                if (Integer.parseInt(coordinate[2]) > 0) {
                    System.out.println(ERROR_SHOULD_HAVE_TWO_VALUES);
                    return false;
                }
            } catch (NumberFormatException ex) {
                System.out.println(ERROR_PARSE);
                return false;
            }
        }
        return true;
    }

    private boolean isCoordLenValValid(String[] coordinate) {
        if (coordinate.length < 2 || coordinate.length > 3) {
            System.out.println(ERROR_SHOULD_HAVE_TWO_VALUES);
            return false;
        }
        return true;
    }
}
