package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UnitSpritesheet {
	
	public UnitSpritesheet(Texture leftUp, Texture downRight) {
		int regionWidth = leftUp.getWidth() / 3, regionHeight = leftUp.getHeight() / 2;
		left = new TextureRegion[3];
		for(int i = 0; i < 3; i ++) {
			left[i] = new TextureRegion();
			left[i].setTexture(leftUp);
			left[i].setRegion(i * regionWidth, 0, regionWidth, regionHeight);
		}
		up = new TextureRegion[3];
		for(int i = 0; i < 3; i ++) {
			up[i] = new TextureRegion();
			up[i].setTexture(leftUp);
			up[i].setRegion(i * regionWidth, regionHeight, regionWidth, regionHeight);
		}
		down = new TextureRegion[3];
		for(int i = 0; i < 3; i ++) {
			down[i] = new TextureRegion();
			down[i].setTexture(downRight);
			down[i].setRegion(i * regionWidth, 0, regionWidth, regionHeight);
		}
		right = new TextureRegion[3];
		for(int i = 0; i < 3; i ++) {
			right[i] = new TextureRegion();
			right[i].setTexture(downRight);
			right[i].setRegion(i * regionWidth, regionHeight, regionWidth, regionHeight);
		}
	}
	
	public TextureRegion[] getUpFrames() {
		return up;
	}
	
	public TextureRegion[] geDownFrames() {
		return down;
	}
	
	public TextureRegion[] getLeftFrames() {
		return left;
	}
	
	public TextureRegion[] getRightFrames() {
		return right;
	}
	
	private TextureRegion[] up, down, left, right;
	
}
