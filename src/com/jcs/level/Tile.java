package com.jcs.level;

import com.jcs.gfx.Screen;
import com.jcs.level.house.CeramicsTile;
import com.jcs.level.tiles.*;

/**
 * Created by Jcs on 5/8/2016.
 */
public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static Tile test = new TestTile(255);
    public static Tile voit = new Tile(254);
    
    public static Tile grass = new GrassTile(0);
    public static Tile water = new WaterTile(1);
    public static Tile dirt = new DirtTile(2);
    public static Tile sand = new SandTile(3);
    public static Tile rock = new RockTile(4);
    
    public static Tile ceramics = new CeramicsTile(10);


    public final int id;
    public boolean isGrass = false;
    public boolean isWater = false;
    public boolean isSand = false;

    public Tile(int id) {
        this.id = id;
        if (tiles[id] != null)
            throw new RuntimeException("Duplicate tile id!");
        tiles[id] = this;
    }

    public void render(Screen screen, Level level, int x, int y) {

    }
}
