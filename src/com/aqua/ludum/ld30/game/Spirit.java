package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Spirit extends AnimatedUnit {
	
	public Spirit(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SOLDIER_SPRITESHEET);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
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
	public float getStartHP() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
