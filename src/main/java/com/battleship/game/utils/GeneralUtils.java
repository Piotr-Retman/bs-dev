package com.battleship.game.utils;

import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import org.springframework.shell.standard.ShellComponent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShellComponent
public class GeneralUtils {

    /**
     *
     * @param coord - ["A","1","0"] - third element is optional
     * @return row number
     */
    public int countRow(String[] coord) {
        int row = 0;
        if (coord.length == 3) {
            row = Integer.parseInt(coord[1] + coord[2]);
        }

        if (coord.length == 2) {
            row = Integer.parseInt(coord[1]);
        }
        return row;
    }

    public Map<String, Integer> getAvailableLettersMap() {
        Map<String, Integer> availableLetters = new HashMap<>();
        availableLetters.put("A", 1);
        availableLetters.put("B", 2);
        availableLetters.put("C", 3);
        availableLetters.put("D", 4);
        availableLetters.put("E", 5);
        availableLetters.put("F", 6);
        availableLetters.put("G", 7);
        availableLetters.put("H", 8);
        availableLetters.put("I", 9);
        availableLetters.put("J", 10);
        return availableLetters;
    }

    public Map<Integer, String> getAvailableIntegersMap() {
        Map<Integer, String> availableLetters = new HashMap<>();
        availableLetters.put(1,"A");
        availableLetters.put(2,"B");
        availableLetters.put(3,"C");
        availableLetters.put(4,"D");
        availableLetters.put(5,"E");
        availableLetters.put(6,"F");
        availableLetters.put(7,"G");
        availableLetters.put(8,"H");
        availableLetters.put(9,"I");
        availableLetters.put(10,"J");
        return availableLetters;
    }

    public boolean isShipArea(Ship ship, String letter, int row) {
        return isStartRowColEq(ship.getCoordinateStartingPosition(), letter, row) ||
                isFinalRowColEq(ship.getCoordinateFinalPosition(), letter, row) ||
                isRangedItemHorizontal(ship.getCoordinateStartingPosition(), ship.getCoordinateFinalPosition(), letter, row) ||
                isRangedItemVertical(ship.getCoordinateStartingPosition(), ship.getCoordinateFinalPosition(), letter, row);
    }

    private boolean isStartRowColEq(Coordinate coordinateStartingPosition, String letter, int row) {
        return coordinateStartingPosition.getCol().equals(letter) && coordinateStartingPosition.getRow() == row;
    }

    private boolean isFinalRowColEq(Coordinate coordinateFinalPosition, String letter, int row) {
        return coordinateFinalPosition.getCol().equals(letter) && coordinateFinalPosition.getRow() == row;
    }

    private boolean isRangedItemHorizontal(Coordinate coordinateStartingPosition, Coordinate coordinateFinalPosition, String letter, int row) {
        return coordinateStartingPosition.getCol().equals(letter) &&
                coordinateFinalPosition.getCol().equals(letter) &&
                isInRange(coordinateStartingPosition.getRow(), coordinateFinalPosition.getRow(), row);
    }

    private boolean isRangedItemVertical(Coordinate coordinateStartingPosition, Coordinate coordinateFinalPosition, String letter, int row) {
        Map<String, Integer> availableLettersMap = getAvailableLettersMap();
        return coordinateStartingPosition.getRow() == row &&
                isInRange(availableLettersMap.get(coordinateStartingPosition.getCol()), availableLettersMap.get(coordinateFinalPosition.getCol()), availableLettersMap.get(letter));
    }

    private boolean isInRange(int startingPointRow, int endPointRow, int currentRow) {
        return startingPointRow <= currentRow && endPointRow >= currentRow;
    }

    public boolean isHit(List<String> hits, String letterRow) {
        return hits.contains(letterRow);
    }
}
