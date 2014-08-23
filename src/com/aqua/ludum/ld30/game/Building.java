package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Building extends Unit{

	public Building(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain);
		// generate a rectangle for pathfinding purposes
		float width = this.getCollisionShape()..radius * 2,
				height = this.getCollisionShape().radius * 2;
		Vector2 recPos = position.cpy().sub(width / 2, height / 2);
		// generate corner points of imaginary rectangle for pathfinding
		cornerPoints = new ArrayList<>();
		cornerPoints.add(recPos);
		cornerPoints.add(recPos.cpy().add(width, 0));
		cornerPoints.add(recPos.cpy().add(width, height));
		cornerPoints.add(recPos.cpy().add(0, height));
	}

	/**
	 * Returns the four corners of its rectangle.
	 * @return
	 */
	public List<Vector2> getCornerPoints(){
		return cornerPoints;
	}
	
	private List<Vector2> cornerPoints;

}
