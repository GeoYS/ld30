package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Building extends Unit{

	public Building(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
		this.rectangle = new Rectangle(position.x, position.y, this.getRadius() * 2, this.getRadius() * 2);
	}
	
	public Rectangle getRectangle() {
		return this.rectangle;
	}
	
	private Rectangle rectangle;

}
