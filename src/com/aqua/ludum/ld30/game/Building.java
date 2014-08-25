package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Building extends Unit{

	public Building(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
		this.rectangle = new Rectangle(position.x, position.y, this.getRadius() * 2, this.getRadius() * 2);
	}
	
	@Override
	public void update(float delta) {
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
		return 1000.0f;
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
		return Float.POSITIVE_INFINITY;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	protected abstract void handleKey(int key);
	
	private Rectangle rectangle;
}
