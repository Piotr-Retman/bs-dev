package com.battleship.game.objects;

public class Coordinate {
    private String col;
    private int row;

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "col='" + col + '\'' +
                ", row=" + row +
                '}';
    }
}
