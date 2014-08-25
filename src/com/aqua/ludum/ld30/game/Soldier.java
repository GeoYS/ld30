package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Soldier extends AnimatedUnit {

	public Soldier(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SOLDIER_SPRITESHEET, Images.ASOLDIER_SPRITESHEET);
	}

	@Override
	public float getSpeed() {
		return 50.0f;
	}

	@Override
	public float getRadius() {
		return 12.0f;
	}

	@Override
	public float getAttackRadius() {
		return 24.0f;
	}

	@Override
	public float getAttackStrength() {
		return 120.0f;
	}

	@Override
	public float getMeleeArmour() {
		return 200.0f;
	}

	@Override
	public float getRangeArmour() {
		return 150.0f;
	}

	@Override
	public float getStartHP() {
		return 400.0f;
	}
	
	@Override
	public float getStartSHP() {
		return 100.0f;
	}
	
}
