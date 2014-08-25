package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Tank extends AnimatedUnit {

	public Tank(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.WORKER_SPRITESHEET);
	}

	@Override
	public float getSpeed() {
		return 30.0f;
	}

	@Override
	public float getRadius() {
		return 16.0f;
	}

	@Override
	public float getAttackRadius() {
		return 32.0f;
	}

	@Override
	public float getAttackStrength() {
		return 100.0f;
	}

	@Override
	public float getMeleeArmour() {
		return 400.0f;
	}

	@Override
	public float getRangeArmour() {
		return 600.0f;
	}

	@Override
	public float getStartHP() {
		return 800.0f;
	}
	
}
