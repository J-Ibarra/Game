package com.jcs.level;

import com.jcs.entity.Entity;
import com.jcs.entity.Player;
import com.jcs.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jcs on 5/8/2016.
 */
public class Level {

    public final int width, height;
    public int[] tiles;
    public int updateCount = 0;

    public int grassColor = 141;
    public int dirtColor = 322;
    public int sandColor = 550;

    private List<Entity> entities = new ArrayList<>();
    public Player player;

    public Level(int w, int h) {
        width = w;
        height = h;

        init();
    }

    public void update() {
        updateCount++;

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
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
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }
    }

    public void add(Entity entity) {
        if (entity instanceof Player) {
            player = (Player) entity;
        }
        entities.add(entity);
        entity.init(this);
    }

    public void remove(Entity e) {
        entities.remove(e);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return Tile.rock;
        return Tile.tiles[tiles[x + y * width]];
    }
}
