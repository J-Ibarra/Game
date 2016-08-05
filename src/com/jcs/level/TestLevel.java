package com.jcs.level;

import com.jcs.gfx.Screen;
import com.jcs.level.Level;

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

        for (int i = 0; i < tiles.length; i++) {
            tiles[i]  = Tile.test.id;
        }
    }
}
