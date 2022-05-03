package com.battleship.game.logic;


import com.battleship.game.enums.ShipStatus;
import com.battleship.game.objects.BoardData;
import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.utils.GeneralUtils;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ShellComponent
public class BattleEngine {

    @Autowired
    @Lazy
    private PreBattleEngine preBattleEngine;

    @Autowired
    @Lazy
    private BattleDrawer battleDrawer;

    @Autowired
    @Lazy
    private LineReader lineReader;

    @Autowired
    @Lazy
    private ComputerEngine computerEngine;

    @Autowired
    @Lazy
    private GeneralUtils generalUtils;

    public void runGame() throws IOException {
        boolean endGame;

        List<String> playerHits = new ArrayList<>();

        BoardData boardData = preBattleEngine.prepareShips();

        System.out.println("..... ALL SET STARTING GAME .... \n\n\n");

        for (; ; ) {
            endGame = playerTurn(boardData, playerHits);
            battleDrawer.generateGameBoard(boardData);
            if (endGame) {
                break;
            }

            endGame = computerTurn(boardData);
            battleDrawer.generateGameBoard(boardData);
            if (endGame) {
                break;
            }
        }
    }

    private boolean computerTurn(BoardData boardData) {
        System.out.println(".... COMPUTER TURN .... \n\n\n");
        Coordinate shot = computerEngine.shot();
        checkComputerShot(shot, boardData);
        updatePlayerShipStatuses(boardData);

        return interpretResultOfBattle(boardData);
    }

    private void updatePlayerShipStatuses(BoardData boardData) {
        boardData.getPlayerData().forEach(ship -> {
            if (ship.getSizeOfShip() == ship.getShipGameStats().getHits().size()) {
                ship.getShipGameStats().setStatus(ShipStatus.SINK);
            }
        });
    }

    private void updateComputerShipStatuses(BoardData boardData) {
        boardData.getComputerData().forEach(ship -> {
            if (ship.getSizeOfShip() == ship.getShipGameStats().getHits().size()) {
                ship.getShipGameStats().setStatus(ShipStatus.SINK);
            }
        });
    }

    private void checkComputerShot(Coordinate shot, BoardData boardData) {
        for (Ship ship : boardData.getPlayerData()) {
            if (generalUtils.isShipArea(ship, shot.getCol(), shot.getRow())) {
                System.out.printf("\n Computer HITS your %s on field %s \n%n", ship.getShipClass(), shot.getCol() + shot.getRow());
                ship.getShipGameStats().getHits().add(shot.getCol() + shot.getRow());
            }
        }
    }

    private boolean playerTurn(BoardData boardData, List<String> playerHits) {
        try {
            System.out.println("... PLAYER TURN .... \n\n\n");
            String format = "Hit enemy with coord /:>";

            String[] hitCoord = lineReader.readLine(format).split("");

            if (hitCoord[0].contains("Q")) {
                System.out.println("*** You decided to finish game manually! ***");
                return true;
            }

            Coordinate c = new Coordinate();
            c.setCol(hitCoord[0]);


            if (hitCoord.length == 3) {
                playerHits.add(hitCoord[0] + hitCoord[1] + hitCoord[2]);
                c.setRow(Integer.parseInt(hitCoord[1] + hitCoord[2]));
            } else {
                playerHits.add(hitCoord[0] + hitCoord[1]);
                c.setRow(Integer.parseInt(hitCoord[1]));
            }

            boardData.setPlayerHits(playerHits);
            checkPlayerShot(c, boardData);
            updateComputerShipStatuses(boardData);

            return interpretResultOfBattle(boardData);

        } catch (Exception ex) {
            System.out.println("*** Exception occured! Player turn will be rerun! ***");
            playerTurn(boardData, playerHits);
        }
        return false;
    }

    private void checkPlayerShot(Coordinate shot, BoardData boardData) {
        for (Ship ship : boardData.getComputerData()) {
            if (generalUtils.isShipArea(ship, shot.getCol(), shot.getRow())) {
                System.out.printf("\n You HIT computer %s on field %s \n%n", ship.getShipClass(), shot.getCol() + shot.getRow());
                ship.getShipGameStats().getHits().add(shot.getCol() + shot.getRow());
            }
        }
    }

    private boolean interpretResultOfBattle(BoardData boardData) {
        boolean isSunkComputerShips = boardData.getComputerData().stream().filter(ship -> ship.getShipGameStats().getStatus() == ShipStatus.SINK).count() == 3;
        boolean isSunkPlayerShips = boardData.getPlayerData().stream().filter(ship -> ship.getShipGameStats().getStatus() == ShipStatus.SINK).count() == 3;

        if (isSunkComputerShips) {
            System.out.println("You won!");
        }

        if (isSunkPlayerShips) {
            System.out.println("Computer won!");
        }

        return isSunkComputerShips || isSunkPlayerShips;
    }
}
