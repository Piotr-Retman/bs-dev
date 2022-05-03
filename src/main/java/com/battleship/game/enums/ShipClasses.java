package com.battleship.game.enums;

public enum ShipClasses {
    BATTLESHIP("BATTLESHIP"),
    DESTROYER("DESTROYER");

    private final String shipClass;

    ShipClasses(String shipClass) {
        this.shipClass = shipClass;
    }

    public String getShipClass() {
        return shipClass;
    }
}
