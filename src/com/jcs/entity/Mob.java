package com.jcs.entity;

/**
 * Created by Jcs on 6/8/2016.
 */
public class Mob extends Entity {
    protected int dir = 0;

    @Override
    public void update() {
        super.update();

    }

    public boolean move(int xa, int ya) {
        if (xa != 0 || ya != 0) {
            if (xa < 0) dir = 2;
            if (xa > 0) dir = 3;
            if (ya < 0) dir = 1;
            if (ya > 0) dir = 0;
        }
        return super.move(xa, ya);
    }
}
