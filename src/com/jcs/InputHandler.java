package com.jcs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {


    List<Key> keys = new ArrayList<>();

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();

    private Main main;

    public InputHandler(Main main) {
        this.main = main;
        this.main.setFocusable(true);
        this.main.addKeyListener(this);
    }

    public void releaseAll() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).pressed = false;
        }
    }

    public void update() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).update();
        }
    }

    private void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            up.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_S)
            down.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_A)
            left.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_D)
            right.toggle(pressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }


    public class Key {

        public int timePressed;
        public boolean pressed;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean down) {

            if (down != pressed) {
                pressed = down;
            }
        }

        public void update() {
            if (pressed) {
                timePressed++;
            } else {
                timePressed = 0;
            }
        }
    }

}