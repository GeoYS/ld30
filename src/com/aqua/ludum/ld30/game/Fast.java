package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Fast extends AnimatedUnit {

	public Fast(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.FAST_SPRITESHEET, Images.AFAST_SPRITESHEET);
	}

	@Override
	public float getSpeed() {
		return 100.0f;
	}

	@Override
	public float getRadius() {
		return 10.0f;
	}

	@Override
	public float getAttackRadius() {
		return 32.0f;
	}

	@Override
	public float getAttackStrength() {
		return 500.0f;
	}

	@Override
	public float getMeleeArmour() {
		return 0.0f;
	}

	@Override
	public float getRangeArmour() {
		return 150.0f;
	}

	@Override
	public float getStartHP() {
		return 10.0f;
	}

	@Override
	public float getStartSHP() {
		return 90.0f;
	}
	
}
