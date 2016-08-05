package com.jcs.level;

import com.jcs.gfx.Screen;

/**
 * Created by Jcs on 5/8/2016.
 */
public class Level {

    public final int width, height;
    public int[] tiles;

    public Level(int w, int h) {
        width = w;
        height = h;

        init();
    }

    public void init() {

    }

    public void render(Screen screen, int xScroll, int yScroll) {
        int xo = xScroll >> 4;
        int yo = yScroll >> 4;
        int xw = (screen.width + 15) >> 4;
        int yh = (screen.height + 15) >> 4;
        screen.setOffset(xScroll, yScroll);
        renderBackground(screen, xo, yo, xw, yh);
        renderSprites(screen, xo, yo, xw, yh);
        screen.setOffset(0, 0);

    }

    private void renderBackground(Screen screen, int xo, int yo, int xw, int yh) {
        for (int y = yo; y <= yh + yo; y++) {
            for (int x = xo; x <= xw + xo; x++) {
                getTile(x, y).render(screen, this, x, y);
            }
        }
    }

    private void renderSprites(Screen screen, int xo, int yo, int xw, int yh) {
        
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width - 1 || y >= height - 1)
            return Tile.test;
        return Tile.tiles[tiles[x + y * width]];
    }
}
