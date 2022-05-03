package com.battleship.game.logic;

import com.battleship.game.enums.CoordType;
import com.battleship.game.enums.ShipClasses;
import com.battleship.game.enums.ShipStatus;
import com.battleship.game.objects.BoardData;
import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.objects.ShipGameStats;
import com.battleship.game.utils.GeneralUtils;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class PreBattleEngine {

    private final Validator validator = new Validator();

    private final ComputerEngine computerEngine = new ComputerEngine();

    @Autowired
    @Lazy
    LineReader lineReader;

    public BoardData prepareShips() throws IOException {
        BoardData boardData = new BoardData();
        preparePlayerData(boardData);
        prepareComputerData(boardData);
//        System.out.println("DEBUG: " + boardData); // todo uncomment to simplify manual tests
        return boardData;
    }

    private void preparePlayerData(BoardData boardData) {
        Ship playerBattleShip = preparePlayerBattleship();
        Ship playerDestroyerShipOne = preparePlayerDestroyerShip();
        Ship playerDestroyerShipTwo = preparePlayerDestroyerShip();

        List<Ship> playerShips = new ArrayList<>();
        playerShips.add(playerBattleShip);
        playerShips.add(playerDestroyerShipOne);
        playerShips.add(playerDestroyerShipTwo);

        boardData.setPlayerData(playerShips);
    }

    private void prepareComputerData(BoardData boardData) {
        computerEngine.cleanComputerData();
        List<Ship> computerShips = generateRandomComputerData();
        boardData.setComputerData(computerShips);
    }

    private List<Ship> generateRandomComputerData() {
        List<Ship> computerFleet = new ArrayList<>();

        Ship battleship = generateComputerBattleship();
        Ship computerDestroyerShipOne = generateComputerDestroyer();
        Ship computerDestroyerShipTwo = generateComputerDestroyer();

        computerFleet.add(battleship);
        computerFleet.add(computerDestroyerShipOne);
        computerFleet.add(computerDestroyerShipTwo);
        System.out.println("*** Computer fleet is ready! ***\n");

        return computerFleet;
    }

    private Ship generateComputerDestroyer() {
        System.out.println("*** Computer generates Destroyer ***");
        String column = computerEngine.randomizeGeneratorColumn();
        Ship computerDestroyerShipOne = generateDefaultDestroyer();
        updateComputerShipWithCoords(column, computerDestroyerShipOne);
        return computerDestroyerShipOne;
    }

    private Ship generateComputerBattleship() {
        System.out.println("*** Computer generates Battleship ***");
        String column = computerEngine.randomizeGeneratorColumn();
        Ship battleship = generateDefaultBattleship();
        updateComputerShipWithCoords(column, battleship);
        return battleship;
    }

    private void updateComputerShipWithCoords(String column, Ship ship) {
        computerEngine.generateShipCoords(column, ship);
    }

    private Ship preparePlayerDestroyerShip() {
        Ship playerDestroyerShip = generateDefaultDestroyer();
        updatePlayerShipWithCoordinate(playerDestroyerShip, CoordType.START);
        updatePlayerShipWithCoordinate(playerDestroyerShip, CoordType.END);
        return playerDestroyerShip;
    }

    private Ship generateDefaultDestroyer() {
        Ship destroyer = new Ship();
        destroyer.setShipGameStats(generateDefaultShipGameStats());
        destroyer.setShipClass(ShipClasses.DESTROYER.getShipClass());
        destroyer.setSizeOfShip(4);
        return destroyer;
    }

    private Ship preparePlayerBattleship() {
        Ship playerBattleship = generateDefaultBattleship();
        updatePlayerShipWithCoordinate(playerBattleship, CoordType.START);
        updatePlayerShipWithCoordinate(playerBattleship, CoordType.END);
        return playerBattleship;
    }

    private Ship generateDefaultBattleship() {
        Ship battleship = new Ship();
        battleship.setShipClass(ShipClasses.BATTLESHIP.getShipClass());
        battleship.setShipGameStats(generateDefaultShipGameStats());
        battleship.setSizeOfShip(5);
        return battleship;
    }

    private ShipGameStats generateDefaultShipGameStats() {
        ShipGameStats shipGameStats = new ShipGameStats();
        shipGameStats.setStatus(ShipStatus.STILL_ALIVE);
        shipGameStats.setHits(new ArrayList<>());
        return shipGameStats;
    }

    private void updatePlayerShipWithCoordinate(Ship playerShip, CoordType type) {
        String format = String.format("Choose your coordinate %s for %s /:>", type, playerShip.getShipClass());

        GeneralUtils generalUtils = new GeneralUtils();

        String coordinateA = lineReader.readLine(format);
        String[] coord = coordinateA.split("");

        boolean isValid = validator.validateCoordinateValue(coord, playerShip, type);

        if (isValid) {
            Coordinate point = new Coordinate();
            point.setCol(coord[0]);
            point.setRow(generalUtils.countRow(coord));

            if (CoordType.START == type) {
                playerShip.setCoordinateStartingPosition(point);
            } else {
                handleEndCoordinate(playerShip, point);
            }
        } else {
            System.out.println("***");
            System.out.println("Error occured - please reenter the coordinate once again!");
            System.out.println("***");

            updatePlayerShipWithCoordinate(playerShip, type);
        }
    }

    private void handleEndCoordinate(Ship playerShip, Coordinate point) {
        if (playerShip.getCoordinateStartingPosition().getRow() > point.getRow()) {
            Coordinate coordinateStartingPosition = playerShip.getCoordinateStartingPosition();
            playerShip.setCoordinateStartingPosition(point);
            playerShip.setCoordinateFinalPosition(coordinateStartingPosition);
        }else{
            playerShip.setCoordinateFinalPosition(point);
        }
    }
}
