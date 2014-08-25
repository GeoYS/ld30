package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Spirit extends AnimatedUnit {
	
	public Spirit(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SPIRIT_SPRITESHEET);
	}
	
	@Override
	public void attack(float delta) {
		getTargetUnit().shp -= delta * 30;
		if (getTargetUnit().shp < 0) {
			getTargetUnit().shp = getTargetUnit().getStartSHP();
			getTargetUnit().setPlayer(getPlayer());
			hp = -10;
		}
	}
	
	/*@Override
	public void update(float delta) {
		
	}*/

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
		return 32.0f;
	}

	@Override
	public float getAttackStrength() {
		return 0;
	}

	@Override
	public float getMeleeArmour() {
		return Float.POSITIVE_INFINITY;
	}

	@Override
	public float getRangeArmour() {
		return Float.POSITIVE_INFINITY;
	}

	@Override
	public float getStartHP() {
		return Float.POSITIVE_INFINITY;
	}
	
	@Override
	public float getStartSHP() {
		return Float.POSITIVE_INFINITY;
	}
	
}
