package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
	
	public Unit(Player player) {
		
	}
	
	public abstract void render(SpriteBatch batch);
	public abstract void update(float delta);
	
	public int getDepth() {
		return 0;
	}
	
	public abstract float getSpeed();
	
	public void commandMove(Vector2 to) {
		
	}
	
	public void commandAttack(Unit target) {
		
	}
	
	public void setStance(Stance stance) {
		
	}
	
	public void changePlayer(Player player) {
		
	}
	
	public Vector2 getPosition() {
		return null;
	}
	
	public Circle getCollisionShape() {
		return null;
	}
	
}
