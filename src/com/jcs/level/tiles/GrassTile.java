package com.jcs.level.tiles;

import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.level.Level;
import com.jcs.level.Tile;

public class GrassTile extends Tile {

    public GrassTile(int id) {
        super(id);
        isGrass = true;
    }

    public void render(Screen screen, Level level, int x, int y) {

        int col = Color.get(level.grassColor, level.grassColor, level.grassColor + 111, level.grassColor + 111);

        int transitionColor = Color.get(level.grassColor - 111, level.grassColor, level.grassColor + 111,
                level.dirtColor);

        boolean u = level.getTile(x, y - 1).isGrass;
        boolean d = level.getTile(x, y + 1).isGrass;
        boolean l = level.getTile(x - 1, y).isGrass;
        boolean r = level.getTile(x + 1, y).isGrass;

        if (u && l)
            screen.render(x * 16 + 0, y * 16 + 0, 0, col);
        else
            screen.render(x * 16 + 0, y * 16 + 0, (l ? 1 : 0) + (u ? 2 : 1) * 32, transitionColor);

        if (u && r)
            screen.render(x * 16 + 8, y * 16 + 0, 1, col);
        else
            screen.render(x * 16 + 8, y * 16 + 0, (r ? 1 : 2) + (u ? 2 : 1) * 32, transitionColor);

        if (d && l)
            screen.render(x * 16 + 0, y * 16 + 8, 2, col);
        else
            screen.render(x * 16 + 0, y * 16 + 8, (l ? 1 : 0) + (d ? 2 : 3) * 32, transitionColor);

        if (d && r)
            screen.render(x * 16 + 8, y * 16 + 8, 3, col);
        else
            screen.render(x * 16 + 8, y * 16 + 8, (r ? 1 : 2) + (d ? 2 : 3) * 32, transitionColor);

    }

}