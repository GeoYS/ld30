package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Temple extends Building {

	public Temple(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
	}

	@Override
	protected void handleKey(int key) {
	}

	@Override
	public float getSpeed() {
		return 0;
	}

	@Override
	public float getRadius() {
		return 0;
	}

	@Override
	public float getStartHP() {
		return 1000;
	}

	@Override
	public float getAttackRadius() {
		return 0;
	}

	@Override
	public float getAttackStrength() {
		return 0;
	}

	@Override
	public float getMeleeArmour() {
		return 0;
	}

	@Override
	public float getRangeArmour() {
		return 0;
	}

	@Override
	public float getStartSHP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void render(SpriteBatch batch) {
		Vector2 screenPos = this.getScreenPosition();
		batch.draw(Images.TEMPLE, screenPos.x - Images.TEMPLE.getWidth() / 2, screenPos.y - Images.TEMPLE.getHeight() / 2);
	}

}
