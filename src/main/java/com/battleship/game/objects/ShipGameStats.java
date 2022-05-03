package com.battleship.game.objects;

import com.battleship.game.enums.ShipStatus;

import java.util.List;

public class ShipGameStats {
    private ShipStatus status;
    private List<String> hits;

    public ShipStatus getStatus() {
        return status;
    }

    public void setStatus(ShipStatus status) {
        this.status = status;
    }

    public List<String> getHits() {
        return hits;
    }

    public void setHits(List<String> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "ShipGameStats{" +
                "status=" + status +
                ", hits=" + hits +
                '}';
    }
}
