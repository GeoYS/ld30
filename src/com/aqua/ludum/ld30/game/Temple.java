package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Temple extends Building {

	public Temple(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
	}

	@Override
	protected void handleKey(int key) {
	}

	@Override
	public float getStartHP() {
		return 10000;
	}

	public com.badlogic.gdx.math.Rectangle getScreenRectangle() {
		Vector2 screenPos = this.getScreenPosition();
		return new Rectangle(screenPos.x - Images.TEMPLE.getWidth() / 2, screenPos.y - Images.TEMPLE.getHeight() / 2 + 25,
				Images.TEMPLE.getWidth(), Images.TEMPLE.getHeight());
	};
	
	@Override
	public void render(SpriteBatch batch) {
		Vector2 screenPos = this.getScreenPosition();
		batch.draw(Images.TEMPLE, screenPos.x - Images.TEMPLE.getWidth() / 2, screenPos.y - Images.TEMPLE.getHeight() / 2 + 25);
	}

}
