package com.jcs.level.tiles;

import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.level.Level;
import com.jcs.level.Tile;

import java.util.Random;

public class WaterTile extends Tile {

    public WaterTile(int id) {
        super(id);
        isWater = true;
        isSand = true;
    }

    private Random wRandom = new Random();


    @Override
    public void render(Screen screen, Level level, int x, int y) {

        wRandom.setSeed(level.updateCount / 20);

        int col = Color.get(005, -1, -1, 225);
        int transitionColor1 = Color.get(3, 005, level.dirtColor - 111, level.dirtColor);
        int transitionColor2 = Color.get(3, 005, level.sandColor - 111, level.sandColor);

        boolean u = level.getTile(x, y - 1).isWater;
        boolean d = level.getTile(x, y + 1).isWater;
        boolean l = level.getTile(x - 1, y).isWater;
        boolean r = level.getTile(x + 1, y).isWater;

        boolean su = !u && level.getTile(x, y - 1).isSand;
        boolean sd = !d && level.getTile(x, y + 1).isSand;
        boolean sl = !l && level.getTile(x - 1, y).isSand;
        boolean sr = !r && level.getTile(x + 1, y).isSand;

        if (u && l)
            screen.render(x * 16 + 0, y * 16 + 0, wRandom.nextInt(4), col, wRandom.nextInt(4));
        else
            screen.render(x * 16 + 0, y * 16 + 0, (l ? 1 : 0) + (u ? 2 : 1) * 32,
                    (su || sl) ? transitionColor2 : transitionColor1);

        if (u && r)
            screen.render(x * 16 + 8, y * 16 + 0, wRandom.nextInt(4), col, wRandom.nextInt(4));
        else
            screen.render(x * 16 + 8, y * 16 + 0, (r ? 1 : 2) + (u ? 2 : 1) * 32,
                    (su || sr) ? transitionColor2 : transitionColor1);

        if (d && l)
            screen.render(x * 16 + 0, y * 16 + 8, wRandom.nextInt(4), col, wRandom.nextInt(4));
        else
            screen.render(x * 16 + 0, y * 16 + 8, (l ? 1 : 0) + (d ? 2 : 3) * 32,
                    (sd || sl) ? transitionColor2 : transitionColor1);

        if (d && r)
            screen.render(x * 16 + 8, y * 16 + 8, wRandom.nextInt(4), col, wRandom.nextInt(4));
        else
            screen.render(x * 16 + 8, y * 16 + 8, (r ? 1 : 2) + (d ? 2 : 3) * 32,
                    (sd || sr) ? transitionColor2 : transitionColor1);

    }

}