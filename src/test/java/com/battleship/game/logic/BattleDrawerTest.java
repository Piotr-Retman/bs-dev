package com.battleship.game.logic;

import com.battleship.game.objects.BoardData;
import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.objects.ShipGameStats;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleDrawerTest {

    private final BattleDrawer gameBoardGenerator = new BattleDrawer();

    @Test
    void it_generates_one_ship_on_gameboard() {
        //given
        BoardData boardData = new BoardData();
        List<Ship> playerData = new ArrayList<>();
        List<Ship> computerData = new ArrayList<>();

        Ship battleShip = new Ship();

        Coordinate coordinate = new Coordinate();
        coordinate.setRow(2);
        coordinate.setCol("B");
        battleShip.setCoordinateStartingPosition(coordinate);

        coordinate = new Coordinate();
        coordinate.setRow(6);
        coordinate.setCol("B");
        battleShip.setCoordinateFinalPosition(coordinate);

        ShipGameStats shipGameStats = new ShipGameStats();
        shipGameStats.setHits(new ArrayList<>());
        battleShip.setShipGameStats(shipGameStats);

        playerData.add(battleShip);

        boardData.setPlayerData(playerData);
        boardData.setComputerData(computerData);
        //when
        String res = gameBoardGenerator.generateGameBoard(boardData);

        //then
        assertEquals("    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "\n" +
                "    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~ooooooooooooooo~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n", res);
    }

    @Test
    void it_generates_two_ships_on_gameboard() {
        //given
        BoardData boardData = new BoardData();

        List<Ship> playerData = new ArrayList<>();
        List<Ship> computerData = new ArrayList<>();

        Ship battleShip = new Ship();
        Coordinate coordinate = new Coordinate();
        coordinate.setRow(2);
        coordinate.setCol("B");
        battleShip.setCoordinateStartingPosition(coordinate);
        coordinate = new Coordinate();
        coordinate.setRow(6);
        coordinate.setCol("B");
        battleShip.setCoordinateFinalPosition(coordinate);

        ShipGameStats shipGameStats = new ShipGameStats();
        shipGameStats.setHits(new ArrayList<>());
        battleShip.setShipGameStats(shipGameStats);

        playerData.add(battleShip);

        Ship destroyer1 = new Ship();
        coordinate = new Coordinate();
        coordinate.setRow(4);
        coordinate.setCol("C");
        destroyer1.setCoordinateStartingPosition(coordinate);
        coordinate = new Coordinate();
        coordinate.setRow(7);
        coordinate.setCol("C");
        destroyer1.setCoordinateFinalPosition(coordinate);
        destroyer1.setShipGameStats(shipGameStats);

        playerData.add(destroyer1);
        boardData.setPlayerData(playerData);
        boardData.setComputerData(computerData);

        //when
        String res = gameBoardGenerator.generateGameBoard(boardData);

        //then
        assertEquals("    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "\n" +
                "    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~ooooooooooooooo~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~oooooooooooo~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n", res);
    }

    @Test
    void it_generates_one_ship_vertical_on_gameboard() {

        //given
        BoardData boardData = new BoardData();

        List<Ship> playerData = new ArrayList<>();
        List<Ship> computerData = new ArrayList<>();

        Ship battleShip = new Ship();

        Coordinate coordinate = new Coordinate();
        coordinate.setRow(2);
        coordinate.setCol("B");
        battleShip.setCoordinateStartingPosition(coordinate);

        coordinate = new Coordinate();
        coordinate.setRow(2);
        coordinate.setCol("F");
        battleShip.setCoordinateFinalPosition(coordinate);

        ShipGameStats shipGameStats = new ShipGameStats();
        shipGameStats.setHits(new ArrayList<>());
        battleShip.setShipGameStats(shipGameStats);

        playerData.add(battleShip);
        boardData.setPlayerData(playerData);
        boardData.setComputerData(computerData);

        //when
        String res = gameBoardGenerator.generateGameBoard(boardData);

        //then
        assertEquals("    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "\n" +
                "    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~ooo~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "C |~~~ooo~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~ooo~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~ooo~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~ooo~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n", res);
    }

    @Test
    void it_generates_one_ship_one_hit_on_gameboard() {
        //given
        BoardData boardData = new BoardData();

        List<Ship> playerData = new ArrayList<>();
        List<Ship> computerData = new ArrayList<>();

        Ship battleShip = new Ship();

        Coordinate coordinate = new Coordinate();
        coordinate.setRow(2);
        coordinate.setCol("B");
        battleShip.setCoordinateStartingPosition(coordinate);

        coordinate = new Coordinate();
        coordinate.setRow(6);
        coordinate.setCol("B");
        battleShip.setCoordinateFinalPosition(coordinate);

        ShipGameStats shipGameStats = new ShipGameStats();
        List<String> hits = new ArrayList<>();
        hits.add("B3");
        shipGameStats.setHits(hits);
        battleShip.setShipGameStats(shipGameStats);

        playerData.add(battleShip);
        boardData.setPlayerData(playerData);
        boardData.setComputerData(computerData);

        //when
        String res = gameBoardGenerator.generateGameBoard(boardData);

        //then
        assertEquals("    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "\n" +
                "    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~oooxxxooooooooo~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n", res);
    }

    @Test
    void it_generates_one_ship_one_hit_on_computer_and_player_and_one_miss_on_computer_gameboard() {
        //given
        BoardData boardData = new BoardData();

        List<Ship> playerData = new ArrayList<>();
        List<Ship> computerData = new ArrayList<>();

        Ship computerBattleShip = new Ship();

        Coordinate coordComputerBattleShipA = new Coordinate();
        coordComputerBattleShipA.setRow(1);
        coordComputerBattleShipA.setCol("A");

        Coordinate coordComputerBattleShipB = new Coordinate();
        coordComputerBattleShipB.setRow(5);
        coordComputerBattleShipB.setCol("A");

        computerBattleShip.setCoordinateStartingPosition(coordComputerBattleShipA);
        computerBattleShip.setCoordinateFinalPosition(coordComputerBattleShipB);

        computerData.add(computerBattleShip);

        Ship battleShip = new Ship();

        Coordinate coordinate = new Coordinate();
        coordinate.setRow(2);
        coordinate.setCol("B");
        battleShip.setCoordinateStartingPosition(coordinate);

        coordinate = new Coordinate();
        coordinate.setRow(6);
        coordinate.setCol("B");
        battleShip.setCoordinateFinalPosition(coordinate);

        ShipGameStats shipGameStats = new ShipGameStats();
        List<String> hits = new ArrayList<>();
        hits.add("B3");
        shipGameStats.setHits(hits);
        battleShip.setShipGameStats(shipGameStats);

        playerData.add(battleShip);

        List<String> playerHits = new ArrayList<>();
        playerHits.add("A1");
        playerHits.add("B1");

        boardData.setPlayerHits(playerHits);
        boardData.setPlayerData(playerData);
        boardData.setComputerData(computerData);

        //when
        String res = gameBoardGenerator.generateGameBoard(boardData);

        //then
        assertEquals("    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |xxx~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |???~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "\n" +
                "    1  2  3  4  5  6  7  8  9  10 \n" +
                "A |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "B |~~~oooxxxooooooooo~~~~~~~~~~~~|\n" +
                "C |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "D |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "E |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "F |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "G |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "H |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "I |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n" +
                "J |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\n", res);
    }
}
