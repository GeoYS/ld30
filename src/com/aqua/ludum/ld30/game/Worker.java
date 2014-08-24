package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Worker extends Unit {

	public Worker(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Images.WORKER_IMAGE, this.getPosition().x, this.getPosition().y);
	}

	@Override
	public float getSpeed() {
		return SPEED;
	}

	@Override
	public float getRadius() {
		return RADIUS;
	}

	private final float SPEED = 10, RADIUS = 8;
	
}
