package com.aqua.ludum.ld30;

import com.badlogic.gdx.math.Vector2;

public class Constants {
	
	public static final float COLLISION_PADDING = 0.1f;
	public static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
	public static final float PATHFINDING_CORNER_PADDING = 5.0f;
	
	/**
	 * 
	 * @param point world coordinate
	 * @param worldHeight in tiles (y)
	 * @return
	 */
	public static Vector2 worldToScreen(Vector2 point, int worldHeight) {
		float x = point.x, y = point.y;
		return (new Vector2(x - y, (x + y) / 2)).add(-worldHeight * 32, -worldHeight * 16);
	}
	
	/**
	 * 
	 * @param point screen coordinate
	 * @param worldHeight in tiles (y)
	 * @return
	 */
	public static Vector2 screenToWorld(Vector2 point, int worldHeight) {
		float x = point.x, y = point.y;
		return (new Vector2(y - x / 2, y + x / 2)).add(worldHeight * 32, worldHeight * 16);
	}
	
}
