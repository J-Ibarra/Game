package com.jcs.level.tiles;

import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.level.Level;
import com.jcs.level.Tile;

public class RockTile extends Tile {

    public RockTile(int id) {
        super(id);
    }

    @Override
    public void render(Screen screen, Level level, int x, int y) {
        int col = Color.get(444, -1, -1, 333);
        int transitionColor = Color.get(111, 444, 555, level.dirtColor);

        boolean u = level.getTile(x, y - 1) != this;
        boolean d = level.getTile(x, y + 1) != this;
        boolean l = level.getTile(x - 1, y) != this;
        boolean r = level.getTile(x + 1, y) != this;

        boolean ul = level.getTile(x - 1, y - 1) != this;
        boolean dl = level.getTile(x - 1, y + 1) != this;
        boolean ur = level.getTile(x + 1, y - 1) != this;
        boolean dr = level.getTile(x + 1, y + 1) != this;

        if (!u && !l) {
            if (!ul)
                screen.render(x * 16 + 0, y * 16 + 0, 0, col, 0);
            else
                screen.render(x * 16 + 0, y * 16 + 0, 6 + (1 * 32), transitionColor, 3);
        } else
            screen.render(x * 16 + 0, y * 16 + 0, (l ? 5 : 4) + ((u ? 3 : 2) * 32), transitionColor, 3);

        if (!u && !r) {
            if (!ur)
                screen.render(x * 16 + 8, y * 16 + 0, 1, col, 0);
            else
                screen.render(x * 16 + 8, y * 16 + 0, 7 + (1 * 32), transitionColor, 3);
        } else
            screen.render(x * 16 + 8, y * 16 + 0, (r ? 3 : 4) + ((u ? 3 : 2) * 32), transitionColor, 3);

        if (!d && !l) {
            if (!dl)
                screen.render(x * 16 + 0, y * 16 + 8, 2, col, 0);
            else
                screen.render(x * 16 + 0, y * 16 + 8, 6 + (2 * 32), transitionColor, 3);
        } else
            screen.render(x * 16 + 0, y * 16 + 8, (l ? 5 : 4) + ((d ? 1 : 2) * 32), transitionColor, 3);
        if (!d && !r) {
            if (!dr)
                screen.render(x * 16 + 8, y * 16 + 8, 3, col, 0);
            else
                screen.render(x * 16 + 8, y * 16 + 8, 7 + (2 * 32), transitionColor, 3);
        } else
            screen.render(x * 16 + 8, y * 16 + 8, (r ? 3 : 4) + ((d ? 1 : 2) * 32), transitionColor, 3);
    }

}