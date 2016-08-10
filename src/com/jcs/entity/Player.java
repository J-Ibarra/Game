package com.jcs.entity;

import com.jcs.InputHandler;
import com.jcs.Main;
import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.gfx.SpriteSheet;

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
		if (key.up.pressed)
			ya--;
		if (key.down.pressed)
			ya++;
		if (key.left.pressed)
			xa--;
		if (key.right.pressed)
			xa++;

		move(xa, ya);
	}

	@Override
	public void render(Screen screen) {
		SpriteSheet sheet = Main.basic;
		int color = Color.get(-1, 111, 115, 432);

		int xt = 0;
		int yt = 0;

		int flip1 = (walkDist >> 3) & 1;
		int flip2 = (walkDist >> 3) & 1;

		if (dir == 0)
			xt = 0;
		if (dir == 1)
			xt = 2;
		if (dir > 1) {
			if (flip2 > 0) {
				xt = 4;
			} else {
				xt = 6;
			}

			if (dir == 2)
				flip1 = 1;
			if (dir == 3)
				flip1 = 0;

		}

		screen.render(sheet, x + 0 + (flip1 * 8), y + 0, (xt + 0) + (yt + 0) * sheet.column, color, flip1);
		screen.render(sheet, x + 8 - (flip1 * 8), y + 0, (xt + 1) + (yt + 0) * sheet.column, color, flip1);
		screen.render(sheet, x + 0 + (flip1 * 8), y + 8, (xt + 0) + (yt + 1) * sheet.column, color, flip1);
		screen.render(sheet, x + 8 - (flip1 * 8), y + 8, (xt + 1) + (yt + 1) * sheet.column, color, flip1);

	}
}
