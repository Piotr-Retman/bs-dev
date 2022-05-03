package com.battleship.game.objects;

import java.util.List;

public class BoardData {
    private List<Ship> computerData;
    private List<Ship> playerData;

    private List<String> playerHits;

    public List<Ship> getComputerData() {
        return computerData;
    }

    public void setComputerData(List<Ship> computerData) {
        this.computerData = computerData;
    }

    public List<Ship> getPlayerData() {
        return playerData;
    }

    public void setPlayerData(List<Ship> playerData) {
        this.playerData = playerData;
    }

    public List<String> getPlayerHits() {
        return playerHits;
    }

    public void setPlayerHits(List<String> playerHits) {
        this.playerHits = playerHits;
    }

    @Override
    public String toString() {
        return "BoardData{" +
                "computerData=" + computerData +
                ", playerData=" + playerData +
                ", playerHits=" + playerHits +
                '}';
    }
}
