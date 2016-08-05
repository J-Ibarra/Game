package com.jcs.level.tiles;

import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.level.Level;
import com.jcs.level.Tile;

/**
 * Created by Jcs on 5/8/2016.
 */
public class TestTile extends Tile {

    public TestTile(int id) {
        super(id);
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        int color = Color.get(050, 500, 005, -1);

        screen.render(x * 16 + 0, y * 16 + 0, 31, color);
        screen.render(x * 16 + 8, y * 16 + 0, 31, color);
        screen.render(x * 16 + 0, y * 16 + 8, 31, color);
        screen.render(x * 16 + 8, y * 16 + 8, 31, color);
    }
}
