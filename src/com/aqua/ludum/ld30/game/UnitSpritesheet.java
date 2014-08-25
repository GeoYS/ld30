package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UnitSpritesheet {
	
	/**
	 * leftup = rightleft
	 * downright = downup
	 * @param leftUp
	 * @param downRight
	 * @param isAttack
	 */
	public UnitSpritesheet(Texture leftUp, Texture downRight, boolean isAttack) {
		if(!isAttack) {
			final int FRAMES_WIDE = 3, FRAMES_HIGH = 2;
			int regionWidth = leftUp.getWidth() / FRAMES_WIDE, regionHeight = leftUp.getHeight() / FRAMES_HIGH;
			left = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				left[i] = new TextureRegion();
				left[i].setTexture(leftUp);
				left[i].setRegion(i * regionWidth, 0, regionWidth, regionHeight);
			}
			up = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				up[i] = new TextureRegion();
				up[i].setTexture(leftUp);
				up[i].setRegion(i * regionWidth, regionHeight, regionWidth, regionHeight);
			}
			down = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				down[i] = new TextureRegion();
				down[i].setTexture(downRight);
				down[i].setRegion(i * regionWidth, 0, regionWidth, regionHeight);
			}
			right = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				right[i] = new TextureRegion();
				right[i].setTexture(downRight);
				right[i].setRegion(i * regionWidth, regionHeight, regionWidth, regionHeight);
			}
		}
		else {
			final int FRAMES_WIDE = 2, FRAMES_HIGH = 3;
			int regionWidth = leftUp.getWidth() / FRAMES_WIDE, regionHeight = leftUp.getHeight() / FRAMES_HIGH;
			right = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_HIGH; i ++) {
				right[i] = new TextureRegion();
				right[i].setTexture(leftUp);
				right[i].setRegion(0, i * regionHeight, regionWidth, regionHeight);
			}
			left = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				left[i] = new TextureRegion();
				left[i].setTexture(leftUp);
				left[i].setRegion(regionWidth, i * regionHeight, regionWidth, regionHeight);
			}
			down = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				down[i] = new TextureRegion();
				down[i].setTexture(downRight);
				down[i].setRegion(0, i * regionHeight, regionWidth, regionHeight);
			}
			up = new TextureRegion[FRAMES_WIDE];
			for(int i = 0; i < FRAMES_WIDE; i ++) {
				up[i] = new TextureRegion();
				up[i].setTexture(downRight);
				up[i].setRegion(regionWidth,  i * regionHeight, regionWidth, regionHeight);
			}
		}
	}
	
	public TextureRegion[] getUpFrames() {
		return up;
	}
	
	public TextureRegion[] getDownFrames() {
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
