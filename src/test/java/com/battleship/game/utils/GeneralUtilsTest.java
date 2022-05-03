package com.battleship.game.utils;

import com.battleship.game.logic.BattleDrawer;
import com.battleship.game.objects.BoardData;
import com.battleship.game.objects.Coordinate;
import com.battleship.game.objects.Ship;
import com.battleship.game.objects.ShipGameStats;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralUtilsTest {

    private final GeneralUtils generalUtils = new GeneralUtils();

    @Test
    public void it_test_is_ship_hit(){
        //given
        List<String> shortHitList = new ArrayList<>();
        shortHitList.add("A8");
        //when
        boolean isHit = generalUtils.isHit(shortHitList, "A8");
        //then
        assertTrue(isHit);
    }

    @Test
    public void it_test_is_ship_not_hit(){
        //given
        List<String> shortHitList = new ArrayList<>();
        shortHitList.add("A4");
        //when
        boolean isHit = generalUtils.isHit(shortHitList, "A8");
        //then
        assertFalse(isHit);
    }

    @Test
    public void it_test_is_ship_area(){
        //given
        Ship ship = new Ship();
        Coordinate startPoint = new Coordinate();
        startPoint.setRow(4);
        startPoint.setCol("A");

        Coordinate finalPoint = new Coordinate();
        finalPoint.setRow(8);
        finalPoint.setCol("A");

        ship.setCoordinateStartingPosition(startPoint);
        ship.setCoordinateStartingPosition(finalPoint);
        //when
        boolean isShipArea = generalUtils.isShipArea(ship, "A", 8);

        //then
        assertTrue(isShipArea);
    }
}
