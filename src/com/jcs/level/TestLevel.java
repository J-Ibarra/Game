package com.jcs.level;

import java.util.Random;

/**
 * Created by Jcs on 5/8/2016.
 */
public class TestLevel extends Level {

    public TestLevel(int w, int h) {
        super(w, h);
    }

    @Override
    public void init() {
        tiles = new int[width * height];
        Random random = new Random();
        for (int i = 0; i < tiles.length; i++) {
            tiles[i]  = Tile.ceramics.id;
        }
    }
}
