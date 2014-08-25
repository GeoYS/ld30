package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class NeutralBuilding extends Building {

	public NeutralBuilding(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.NEUTRAL_BUILDING);
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
		return 100000;
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
		return 1;
	}

	@Override
	public float getRangeArmour() {
		return 1;
	}

	@Override
	public float getStartSHP() {
		// TODO Auto-generated method stub
		return 0;
	}

}
