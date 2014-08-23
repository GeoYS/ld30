package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
	
	public Block(Vector2 position, float width, float height) {
		this.position = position;
		this.width = width;
		this.height = height;
		rectangle = new Rectangle(position.x, position.y, width, height);
		cornerPoints = new ArrayList<>();
		cornerPoints.add(position);
		cornerPoints.add(position.cpy().add(width, 0));
		cornerPoints.add(position.cpy().add(width, height));
		cornerPoints.add(position.cpy().add(0, height));
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	/**
	 * Returns the four corners of its rectangle.
	 * @return
	 */
	public List<Vector2> getCornerPoints(){
		return cornerPoints;
	}
	
	private Rectangle rectangle;
	private Vector2 position;
	private final float width, height;
	private ArrayList<Vector2> cornerPoints;
}
