package com.jcs.level.house;

import com.jcs.level.LoadLevel;
import com.jcs.level.Tile;

public class HouseLevel extends LoadLevel {

    public HouseLevel() {
        super("levels/house.level");
    }

    @Override
    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.voit;
        return Tile.tiles[tiles[x + y * width]];
    }
}
