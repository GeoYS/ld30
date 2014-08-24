package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Worker extends Unit {

	public Worker(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
		System.out.println("x: " + this.getPosition().x + " y: " + this.getPosition().y);
	}

	@Override
	public void render(SpriteBatch batch) {
		Vector2 screenPos = Constants.worldToScreen(this.getPosition(), getTerrain().getTilesHigh());
		batch.draw(Images.WORKER_IMAGE, screenPos.x, screenPos.y);
	}

	@Override
	public float getSpeed() {
		return SPEED;
	}

	@Override
	public float getRadius() {
		return RADIUS;
	}

	private final float SPEED = 50, RADIUS = 16;

	@Override
	public float getAttackRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getAttackStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getMeleeArmour() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRangeArmour() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
