package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {
	
	public Block(Vector2 position, float width, float height) {
		rectangle = new Rectangle(position.x, position.y, width, height);
	}
	
	public Block(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public Vector2 getPosition() {
		return new Vector2(rectangle.x, rectangle.y);
	}
	
	public float getWidth() {
		return rectangle.width;
	}
	
	public float getHeight() {
		return rectangle.height;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	private Rectangle rectangle;
}
