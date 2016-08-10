package com.jcs.gfx;

public class Screen {

    public int xOffset;
    public int yOffset;

    public static final int BIT_MIRROR_X = 0x01;
    public static final int BIT_MIRROR_Y = 0x02;

    public final int width, height;
    public int[] pixels;

    private SpriteSheet sheet;

    public Screen(int w, int h, SpriteSheet sheet) {
        this.sheet = sheet;
        this.width = w;
        this.height = h;
        pixels = new int[w * h];
    }

    public void clear() {
        clear(Color.get(0));
    }

    public void clear(int color) {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = color;
    }

    public void render(int xp, int yp, int tile, int colors) {
        render(xp, yp, tile, colors, 0);
    }

    public void render(int xp, int yp, int tile, int colors, int bits) {
        xp -= xOffset;
        yp -= yOffset;
        boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
        boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;

        int xTile = tile % 32;
        int yTile = tile / 32;
        int toffs = xTile * 8 + yTile * 8 * sheet.width;

        for (int y = 0; y < 8; y++) {
            if (y + yp < 0 || y + yp >= height)
                continue;
            int ys = y;
            if (mirrorY)
                ys = 7 - y;

            for (int x = 0; x < 8; x++) {
                if (x + xp < 0 || x + xp >= width)
                    continue;

                int xs = x;
                if (mirrorX)
                    xs = 7 - x;

                int col = (colors >> (sheet.pixels[xs + ys * sheet.width + toffs] * 8)) & 255;
                if (col < 255)
                    pixels[(x + xp) + (y + yp) * width] = col;
            }
        }
    }
    
    public void render(SpriteSheet sheet, int xp, int yp, int tile, int colors) {
        render(sheet, xp, yp, tile, colors, 0);
    }

    public void render(SpriteSheet sheet, int xp, int yp, int tile, int colors, int bits) {
        xp -= xOffset;
        yp -= yOffset;
        boolean mirrorX = (bits & BIT_MIRROR_X) > 0;
        boolean mirrorY = (bits & BIT_MIRROR_Y) > 0;

        int xTile = tile % 32;
        int yTile = tile / 32;
        int toffs = xTile * 8 + yTile * 8 * sheet.width;

        for (int y = 0; y < 8; y++) {
            if (y + yp < 0 || y + yp >= height)
                continue;
            int ys = y;
            if (mirrorY)
                ys = 7 - y;

            for (int x = 0; x < 8; x++) {
                if (x + xp < 0 || x + xp >= width)
                    continue;

                int xs = x;
                if (mirrorX)
                    xs = 7 - x;

                int col = (colors >> (sheet.pixels[xs + ys * sheet.width + toffs] * 8)) & 255;
                if (col < 255)
                    pixels[(x + xp) + (y + yp) * width] = col;
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}