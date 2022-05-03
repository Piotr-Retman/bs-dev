package com.battleship.game.logic;

import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.utils.GeneralUtils;
import org.springframework.shell.standard.ShellComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@ShellComponent
public class ComputerEngine {

    private final static List<String> usedCols = new ArrayList<>();
    private final static List<String> shotUsed = new ArrayList<>();
    private String randomizedColumn = "";

    public String randomizeColumn() {
        GeneralUtils generalUtils = new GeneralUtils();
        Map<String, Integer> availableLettersMap = generalUtils.getAvailableLettersMap();

        int i = randomNumberFrom1to10();

        return availableLettersMap.entrySet()
                .stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() == i)
                .collect(Collectors.toList())
                .get(0)
                .getKey();
    }

    private int randomNumberFrom1to10() {
        Random random = new Random();
        return random.ints(1, 10)
                .findFirst()
                .getAsInt();
    }

    public void cleanComputerData() {
        usedCols.clear();
    }

    public void generateShipCoords(String column, Ship ship) {
        GeneralUtils generalUtils = new GeneralUtils();
        Map<String, Integer> availableLettersMap = generalUtils.getAvailableLettersMap();

        int placeToStart = availableLettersMap.get(column);
        int shipWidth = ship.getSizeOfShip() - 1;

        Coordinate coordinateStart = new Coordinate();
        coordinateStart.setCol(column);

        Coordinate coordinateFinal = new Coordinate();
        coordinateFinal.setCol(column);

        int battleShipAddedWidth = placeToStart + shipWidth;

        if (battleShipAddedWidth > 10) {
            battleShipAddedWidth = placeToStart - shipWidth;

            coordinateStart.setRow(battleShipAddedWidth);
            coordinateFinal.setRow(placeToStart);

        } else {
            coordinateStart.setRow(placeToStart);
            coordinateFinal.setRow(battleShipAddedWidth);
        }

        ship.setCoordinateFinalPosition(coordinateFinal);
        ship.setCoordinateStartingPosition(coordinateStart);
    }

    public Coordinate shot() {
        String column = randomizeColumn();
        int row = randomNumberFrom1to10();
        String shot = column + row;
        if (shotUsed.contains(shot)) {
            shot();
        } else {
            shotUsed.add(shot);
        }

        Coordinate c = new Coordinate();
        c.setCol(column);
        c.setRow(row);

        return c;
    }

    public String randomizeGeneratorColumn() {
        randomizedColumn = randomizeColumn();
        if (usedCols.contains(randomizedColumn)) {
            randomizeGeneratorColumn();
        } else {
            usedCols.add(randomizedColumn);
        }
        return randomizedColumn;
    }
}

