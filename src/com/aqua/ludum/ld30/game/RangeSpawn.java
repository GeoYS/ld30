package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class RangeSpawn extends Building {

	public RangeSpawn(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.RANGED_TENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleKey(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getStartHP() {
		// TODO Auto-generated method stub
		return 0;
	}

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

	@Override
	public float getStartSHP() {
		// TODO Auto-generated method stub
		return 0;
	}

}
