package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimatedUnit extends Unit{

	public AnimatedUnit(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
	}

	@Override
	public void render(SpriteBatch batch) {
	}
	
	public void setUnitSpriteSheet() {
		
	}
	
	private UnitSpritesheet spritesheet;
	
}
