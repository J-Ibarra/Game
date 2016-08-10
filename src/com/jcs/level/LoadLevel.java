package com.jcs.level;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LoadLevel extends Level {

	public LoadLevel(String path) {
		loadLevel(path);
	}
	
	private void loadLevel(String file) {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(file)));
			String line;
			String data = "";
			boolean t = false;

			while ((line = reader.readLine()) != null) {

				String[] tokens = line.split("\\s+");

				if (!t) {
					switch (tokens[0]) {
					case "width:":
						width = Integer.parseInt(tokens[1]);
						break;

					case "height:":
						height = Integer.parseInt(tokens[1]);
						break;

					case "tiles{":
						t = true;
						break;
					}
				} else {
					if(!tokens[0].equals("}"))
					data += line + " ";
				}
			}

			String[] tokens = data.split("\\s+");
			
			tiles = new int[width * height];
			for (int i = 0; i < tokens.length; i++) {
				tiles[i] = Integer.parseInt(tokens[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
