package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Ranged extends AnimatedUnit {

	public Ranged(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.RANGED_SPRITESHEET);
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
		return 128.0f;
	}

	@Override
	public float getAttackStrength() {
		return 20.0f;
	}

	@Override
	public float getMeleeArmour() {
		return 50.0f;
	}

	@Override
	public float getRangeArmour() {
		return 20.0f;
	}

	@Override
	public float getStartHP() {
		return 200.0f;
	}

	@Override
	public float getStartSHP() {
		return 110.0f;
	}
	
}
