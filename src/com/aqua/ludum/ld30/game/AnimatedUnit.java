package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.util.Stopwatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimatedUnit extends Unit{

	public AnimatedUnit(Player player, Vector2 position, Terrain terrain, UnitSpritesheet spritesheet) {
		super(player, position, terrain);
		oldPos = new Vector2();
		animationTimer = new Stopwatch();
		this.setUnitSpriteSheet(spritesheet);
	}

	@Override
	public void update(float delta) {
		oldPos.set(this.getPosition());
		super.update(delta);
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Vector2 deltaPos = this.getPosition().cpy().sub(oldPos);
		
		// is the unit moving?
		if(animationTimer.isRunning()) {
			if(deltaPos.x == 0 && deltaPos.y == 0) {
				animationTimer.stop();
				animationTimer.reset();
			}
		}
		else {
			if(deltaPos.x != 0 || deltaPos.y != 0) {
				animationTimer.start();
			}
		}
		
		// which direction is the unit going?
		float dx = deltaPos.x, dy = deltaPos.y;
		if((dx < 0 && dy < 0)) {
			current = up;
		}
		else if((dx > 0 && dy > 0)) {
			current = down;
		}
		else if((dx < 0 && dy > 0) || (dx == 0 && dy > 0) || (dx == 0 && dy < 0)) {
			current = left;
		}
		else if((dx > 0 && dy < 0) || (dy == 0 && dx > 0) || (dy == 0 && dx < 0)) {
			current = right;
		}
		
		int keyFrame;
		if(animationTimer.isRunning()) {
			keyFrame = current.getKeyFrameIndex(animationTimer.time() / 1000f); // StopWatch is in milliseconds!
		}
		else {
			// MIDDLE FRAME (OUT OF 3) IS THE STANDING FRAME
			keyFrame = 1;
		}
		Vector2 screenPos = this.getScreenPosition();
		batch.draw(current.getKeyFrames()[keyFrame], screenPos.x - current.getKeyFrames()[0].getRegionWidth() / 2, screenPos.y);
	}
	
	public void setUnitSpriteSheet(UnitSpritesheet spritesheet) {
		up = new Animation(FRAME_DURATION, spritesheet.getUpFrames());
		up.setPlayMode(PlayMode.LOOP_PINGPONG); // DON'T FORGET TO SET THE PLAY MODE!!!
		down = new Animation(FRAME_DURATION, spritesheet.getDownFrames());
		down.setPlayMode(PlayMode.LOOP_PINGPONG);
		left = new Animation(FRAME_DURATION, spritesheet.getLeftFrames());
		left.setPlayMode(PlayMode.LOOP_PINGPONG);
		right = new Animation(FRAME_DURATION, spritesheet.getRightFrames());
		right.setPlayMode(PlayMode.LOOP_PINGPONG);
		current = down;
	}
	
	private Animation up, down, left, right, current;
	private Vector2 oldPos;
	private Stopwatch animationTimer;
	private final float FRAME_DURATION = 0.2f; // in seconds
}
