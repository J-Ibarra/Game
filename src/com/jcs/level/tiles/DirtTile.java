package com.jcs.level.tiles;

import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.level.Level;
import com.jcs.level.Tile;

/**
 * Created by Jcs on 5/8/2016.
 */
public class DirtTile extends Tile {

    public DirtTile(int id) {
        super(id);
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {

        int col = Color.get(level.dirtColor, -1, -1, level.dirtColor + 111);

        screen.render(x * 16 + 0, y * 16 + 0, 0, col);

        screen.render(x * 16 + 8, y * 16 + 0, 1, col);

        screen.render(x * 16 + 0, y * 16 + 8, 2, col);

        screen.render(x * 16 + 8, y * 16 + 8, 3, col);
    }
}
