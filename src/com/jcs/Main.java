package com.jcs;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

import com.jcs.entity.*;
import com.jcs.gfx.*;
import com.jcs.gfx.Color;
import com.jcs.level.*;

public class Main extends Canvas implements Runnable {
    private volatile boolean running = false;

    public static final String TITTLE = "Java 2D Game | Dreams Demons";
    public static final int SCALE = 3;
    public static final int WIDTH = 600 / SCALE;
    public static final int HEIGHT = 400 / SCALE;

    private JFrame frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public static SpriteSheet sheet;
    public static SpriteSheet basic;
    private InputHandler key;

    private Screen screen;
    private Level level;
    private Player player;

    public Main() throws Exception {
        Dimension dimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);

        init();

        frame = new JFrame(TITTLE);

        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    private void init() throws Exception {
        sheet = new SpriteSheet("SpriteSheet.png", 32, 32);
        basic = new SpriteSheet("warrior.png", 4, 8);

        key = new InputHandler(this);
        screen = new Screen(WIDTH, HEIGHT, sheet);

        player = new Player(this, key);
        level = Level.testLevel;
        level.add(player);
    }

    private void update() {
        key.update();
        level.update();

        if (InputHandler.key[KeyEvent.VK_1]) {
            level.remove(player);
            level = Level.testLevel;
            level.add(player);
        }
        if (InputHandler.key[KeyEvent.VK_2]) {
            level.remove(player);
            level = Level.houseLevel;
            level.add(player);
        }
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            requestFocus();
            return;
        }

        screen.clear();

        int xScroll = player.x - (screen.width - 15) / 2;
        int yScroll = player.y - (screen.height - 15) / 2;

		/*if (xScroll < 15)
            xScroll = 15;
		if (yScroll < 15)
			yScroll = 15;
		if (xScroll > level.width * 16 - screen.width - 16)
			xScroll = level.width * 16 - screen.width - 16;
		if (yScroll > level.height * 16 - screen.height - 16)
			yScroll = level.height * 16 - screen.height - 16;*/

        level.render(screen, xScroll, yScroll);

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                int cc = screen.pixels[x + y * screen.width];
                if (cc < 6 * 6 * 6)
                    pixels[x + y * WIDTH] = Color.colors[cc];
            }
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

    private void oneSecond(int ups, int fps) {
        frame.setTitle(TITTLE + " || ups: " + ups + ", fps: " + fps + " || " + "player x: " + (player.x >> 4)
                + ", player y: " + (player.y >> 4));
    }

    @Override
    public void run() {
        int ups = 0;
        int fps = 0;

        double nUps = 1_000_000_000 / 30;
        double delta = 0;
        long lastTime = System.nanoTime();
        long lastTimer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nUps;
            lastTime = now;

            if (delta > 1) {
                update();
                ups++;
                delta -= 1;
            }

            fps++;
            render();

            if (System.currentTimeMillis() - lastTimer > 1000) {
                lastTimer += 1000;
                oneSecond(ups, fps);
                ups = fps = 0;
            }
        }
    }

    public synchronized void start() {
        running = true;
        new Thread(this, "Jcs Game Loop").start();
    }

    public synchronized void stop() {
        running = false;
        frame.dispose();
    }

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
