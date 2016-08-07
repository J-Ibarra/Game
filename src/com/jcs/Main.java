package com.jcs;

import com.jcs.gfx.*;
import com.jcs.gfx.Color;
import com.jcs.level.Level;
import com.jcs.level.TestLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Main extends Canvas implements Runnable {
    private volatile boolean running = false;

    public static final String TITTLE = "Java 2D Game | Dreams Demons";
    public static final int SCALE = 3;
    public static final int WIDTH = 600 / SCALE;
    public static final int HEIGHT = 400 / SCALE;

    private JFrame frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private InputHandler key;
    private SpriteSheet sheet;
    private Screen screen;
    private Level level;

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
        key = new InputHandler(this);
        sheet = new SpriteSheet("SpriteSheet.png");
        screen = new Screen(WIDTH, HEIGHT, sheet);
        level = new TestLevel(12, 9);
    }

    private void update() {
        key.update();
        level.update();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            requestFocus();
            return;
        }

        level.render(screen, 0, 0);

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
        frame.setTitle(TITTLE + " | ups: " + ups + ", fps: " + fps);
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
    }

    public static void main(String[] args) {
        try {
            new Main().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
