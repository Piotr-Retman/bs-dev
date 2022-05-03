package com.battleship.game.objects;

public class Ship {

    private String shipClass;
    private ShipGameStats shipGameStats;
    private int sizeOfShip;
    private Coordinate coordinateStartingPosition;
    private Coordinate coordinateFinalPosition;

    public String getShipClass() {
        return shipClass;
    }

    public void setShipClass(String shipClass) {
        this.shipClass = shipClass;
    }

    public ShipGameStats getShipGameStats() {
        return shipGameStats;
    }

    public void setShipGameStats(ShipGameStats shipGameStats) {
        this.shipGameStats = shipGameStats;
    }

    public int getSizeOfShip() {
        return sizeOfShip;
    }

    public void setSizeOfShip(int sizeOfShip) {
        this.sizeOfShip = sizeOfShip;
    }

    public Coordinate getCoordinateStartingPosition() {
        return coordinateStartingPosition;
    }

    public void setCoordinateStartingPosition(Coordinate coordinateStartingPosition) {
        this.coordinateStartingPosition = coordinateStartingPosition;
    }

    public Coordinate getCoordinateFinalPosition() {
        return coordinateFinalPosition;
    }

    public void setCoordinateFinalPosition(Coordinate coordinateFinalPosition) {
        this.coordinateFinalPosition = coordinateFinalPosition;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "shipClass='" + shipClass + '\'' +
                ", shipGameStats=" + shipGameStats +
                ", sizeOfShip=" + sizeOfShip +
                ", coordinateStartingPosition=" + coordinateStartingPosition +
                ", coordinateFinalPosition=" + coordinateFinalPosition +
                '}';
    }
}
