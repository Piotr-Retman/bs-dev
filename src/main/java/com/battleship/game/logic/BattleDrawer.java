package com.battleship.game.logic;

import com.battleship.game.enums.Operation;
import com.battleship.game.objects.BoardData;
import com.battleship.game.objects.Ship;
import com.battleship.game.utils.GeneralUtils;
import org.springframework.shell.standard.ShellComponent;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@ShellComponent
public class BattleDrawer {

    private final GeneralUtils generalUtils = new GeneralUtils();

    public String generateGameBoard(BoardData boardData) {
        StringBuilder playground = new StringBuilder();

        updatePlaygroundWithHeader(playground);
        updatePlaygroundWithData(playground, boardData, Operation.HANDLE_COMPUTER_BATTLE_AREA);

        playground.append("\n");

        updatePlaygroundWithHeader(playground);
        updatePlaygroundWithData(playground, boardData, Operation.HANDLE_PLAYER_BATTLE_AREA);

        System.out.println(playground);
        return playground.toString();
    }

    private void updatePlaygroundWithData(StringBuilder playground, BoardData boardData, Operation operation) {
        for (int col = 1; col < 11; col++) {
            for (int row = 0; row < 12; row++) {
                handleRow(playground, boardData, operation, col, row);
            }
        }
    }

    private void handleRow(StringBuilder playground, BoardData boardData, Operation operation, int col, int row) {
        Map<Integer, String> availableIntegersMap = generalUtils.getAvailableIntegersMap();
        String colValue = availableIntegersMap.get(col);
        String letter = availableIntegersMap.get(col);
        String field;

        switch (row) {
            case 0:
                playground.append(colValue);
                playground.append(" |");
                break;
            case 11:
                playground.append("|\n");
                break;
            default:
                if (operation == Operation.HANDLE_COMPUTER_BATTLE_AREA) {
                    field = handleOperation(Optional.ofNullable(boardData.getPlayerHits()), boardData.getComputerData(), letter, row, operation);
                    playground.append(field);
                }

                if (operation == Operation.HANDLE_PLAYER_BATTLE_AREA) {
                    field = handleOperation(Optional.empty(), boardData.getPlayerData(), letter, row, operation);
                    playground.append(field);
                }
                break;
        }
    }

    private void updatePlaygroundWithHeader(StringBuilder playground) {
        playground.append("   ");
        for (int j = 1; j < 11; j++) {
            playground.append(" ").append(j).append(" ");
        }
        playground.append("\n");
    }

    private String handleOperation(Optional<List<String>> playerShots, List<Ship> shipData, String letter, int row, Operation operation) {
        AtomicReference<String> field = new AtomicReference<>("~~~");
        String coordAsStr = letter + row;
        for (Ship ship : shipData) {

            if (Operation.HANDLE_COMPUTER_BATTLE_AREA == operation) {
                playerShots.ifPresent(shots -> field.set(shots.contains(coordAsStr) ? "???" : "~~~"));
            }

            if (generalUtils.isShipArea(ship, letter, row)) {
                switch (operation) {
                    case HANDLE_PLAYER_BATTLE_AREA:
                        List<String> hits = ship.getShipGameStats().getHits();
                        field.set("ooo");
                        if (generalUtils.isHit(hits, coordAsStr)) {
                            field.set("xxx");
                        }
                        break;
                    case HANDLE_COMPUTER_BATTLE_AREA:
                        playerShots.ifPresent(shots -> field.set(shots.contains(coordAsStr) ? "xxx" : "~~~"));
                        break;
                }
                break;
            }

        }
        return field.get();
    }
}
