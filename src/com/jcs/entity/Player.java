package com.jcs.entity;

import com.jcs.InputHandler;
import com.jcs.Main;
import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;

/**
 * Created by Jcs on 6/8/2016.
 */
public class Player extends Mob {

    private Main main;
    private InputHandler key;

    public Player(Main main, InputHandler key) {
        this.main = main;
        this.key = key;
    }

    @Override
    public void update() {
        super.update();

        int xa = 0;
        int ya = 0;
        if (key.up.pressed) ya--;
        if (key.down.pressed) ya++;
        if (key.left.pressed) xa--;
        if (key.right.pressed) xa++;

        move(xa, ya);
    }

    @Override
    public void render(Screen screen) {


        screen.render(x + 0, y + 0, 31, Color.get(050, 500, 005, -1));
        screen.render(x + 8, y + 0, 31, Color.get(050, 500, 005, -1));
        screen.render(x + 0, y + 8, 31, Color.get(050, 500, 005, -1));
        screen.render(x + 8, y + 8, 31, Color.get(050, 500, 005, -1));
    }
}
