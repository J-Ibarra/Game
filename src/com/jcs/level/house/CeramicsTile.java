package com.jcs.level.house;

import com.jcs.gfx.Color;
import com.jcs.gfx.Screen;
import com.jcs.level.Level;
import com.jcs.level.Tile;

public class CeramicsTile extends Tile {

	public CeramicsTile(int id) {
		super(id);
	}

	

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		int color = Color.get(353, 533, 335, -1);
		int xt = 0;
		int yt = 5;
		
		screen.render(x * 16 + 0, y * 16 + 0, xt + yt * 32, color, 0);
		screen.render(x * 16 + 8, y * 16 + 0, xt + yt * 32, color, 1);
		screen.render(x * 16 + 0, y * 16 + 8, xt + yt * 32, color, 2);
		screen.render(x * 16 + 8, y * 16 + 8, xt + yt * 32, color, 3);
	}
}
