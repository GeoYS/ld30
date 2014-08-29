package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.util.Stopwatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class AnimatedUnit extends Unit{

	public AnimatedUnit(Player player, Vector2 position, Terrain terrain, UnitSpritesheet moveSheet, UnitSpritesheet attackSheet) {
		super(player, position, terrain);
		oldPos = new Vector2();
		animationTimer = new Stopwatch();
		this.setMoveSheet(moveSheet);
		this.setAttackSheet(attackSheet);
	}

	@Override
	public void update(float delta) {
		oldPos.set(this.getPosition());
		super.update(delta);
		updateAnimation();
	}
	
	private void updateAnimation() {
		Vector2 deltaPos = this.getPosition().cpy().sub(oldPos);
		float bufferAngle = 10; // degrees
		float angle = deltaPos.angle();
		if(isAttackAnimation(current) && !isAttacking() ) { // switch from attacking to not attacking
			updateAnimationDirection(deltaPos);
		}
		else if (!isAttackAnimation(current) && isAttacking() && targetUnit != null) { // switch from not attacking to attacking
			deltaPos = targetUnit.getPosition().cpy().sub(this.getPosition());
			updateAnimationDirection(deltaPos);
		}
		else if (isAttacking() && targetUnit != null) {
			deltaPos = targetUnit.getPosition().cpy().sub(this.getPosition());
			updateAnimationDirection(deltaPos);
		}
		else if(current == up || current == aup) {
			if(angle < 180 - bufferAngle || angle > 270 + bufferAngle) {
				updateAnimationDirection(deltaPos);
			}
		}
		else if(current == down || current == adown) {
			if(angle < 360 - bufferAngle && angle > 90 + bufferAngle) {
				updateAnimationDirection(deltaPos);
			}
		}
		else if(current == left || current == aleft) {
			if(angle < 90 - bufferAngle || angle > 180 + bufferAngle) {
				updateAnimationDirection(deltaPos);
			}
		}
		else if(current == right || current == aright) {
			if(angle < 270 - bufferAngle && angle > 0 + bufferAngle) {
				updateAnimationDirection(deltaPos);
			}
		}
	}
	
	private void updateAnimationDirection(Vector2 deltaPos) {
		float dx = deltaPos.x, dy = deltaPos.y;
		if((dx < 0 && dy < 0)) {
			if(isAttacking()) {
				setAnimation(aup);
			}
			else {
				setAnimation(up);
			}
		}
		else if((dx > 0 && dy > 0)) {
			if(isAttacking()) {
				setAnimation(adown);
			}
			else {
				setAnimation(down);
			}
		}
		else if((dx < 0 && dy > 0) || (dx == 0 && dy > 0) || (dx == 0 && dy < 0)) {
			if(isAttacking()) {
				setAnimation(aleft);
			}
			else {
				setAnimation(left);
			}
		}
		else if((dx > 0 && dy < 0) || (dy == 0 && dx > 0) || (dy == 0 && dx < 0)){
			if(isAttacking()) {
				setAnimation(aright);
			}
			else {
				setAnimation(right);
			}
		}
		else {
			if(isAttackAnimation(current) && !isAttacking() ) { // switch from attacking to not attacking
				if(current == aleft) {
					setAnimation(left);
				}
				else if(current == aright) {
					setAnimation(right);
				}
				else if(current == aup) {
					setAnimation(up);
				}
				else if(current == adown) {
					setAnimation(down);
				}
			}
		}
	}
	
	private void setAnimation(Animation animation) {
		if(isAttackAnimation(current)) {
			if(isAttackAnimation(animation)) {
				current = animation;
				return;
			}
			if(animationTimer.time() < 1000f) { // minimum time for attack animation (MILLISECONDS!!!)
				return;
			}
		}
		animationTimer.restart();
		current = animation;
	}
	
	private boolean isAttackAnimation(Animation animation) {
		return animation == aup || animation == adown || animation == aleft || animation == aright;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		/*if(current == aup || current == adown || current == aleft || current == aright) {
			if(animationTimer.time() / 1000f < FRAME_DURATION * 6) {
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
				return;
			}
		}
		Vector2 deltaPos = this.getPosition().cpy().sub(oldPos);
		
		// is the unit moving?
		if(animationTimer.isRunning()) {
			if(deltaPos.x == 0 && deltaPos.y == 0 && !isAttacking()) {
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
			current = this.isAttacking() ? aup : up;
		}
		else if((dx > 0 && dy > 0)) {
			current = this.isAttacking() ? adown : down;
		}
		else if((dx < 0 && dy > 0) || (dx == 0 && dy > 0) || (dx == 0 && dy < 0)) {
			current = this.isAttacking() ? aleft : left;
		}
		else if((dx > 0 && dy < 0) || (dy == 0 && dx > 0) || (dy == 0 && dx < 0)){
			current = this.isAttacking() ? aright : right;
		}
		else {
			if(isAttacking()) {
				if(current == up) {
					current = aup;
				}
				else if(current == down) {
					current = adown;
				}
				else if(current == left) {
					current = aleft;
				}
				else if(current == right) {
					current = aright;
				}
			}
			else {
				if(current == aup) {
					current = up;
				}
				else if(current == adown) {
					current = down;
				}
				else if(current == aleft) {
					current = left;
				}
				else if(current == aright) {
					current = right;
				}
			}
		}*/

		Vector2 deltaPos = this.getPosition().cpy().sub(oldPos);
		int keyFrame;
		if(deltaPos.len2() != 0 || isAttacking() || isAttackAnimation(current)) {
			keyFrame = current.getKeyFrameIndex(animationTimer.time() / 1000f); // StopWatch is in milliseconds!
		}
		else {
			// MIDDLE FRAME (OUT OF 3) IS THE STANDING FRAME
			keyFrame = 1;
		}
		Vector2 screenPos = this.getScreenPosition();
		batch.draw(current.getKeyFrames()[keyFrame], screenPos.x - current.getKeyFrames()[0].getRegionWidth() / 2, screenPos.y);
	}
	
	public void setMoveSheet(UnitSpritesheet spritesheet) {
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
	
	public void setAttackSheet(UnitSpritesheet spritesheet) {
		aup = new Animation(FRAME_DURATION, spritesheet.getUpFrames());
		aup.setPlayMode(PlayMode.LOOP_PINGPONG); // DON'T FORGET TO SET THE PLAY MODE!!!
		adown = new Animation(FRAME_DURATION, spritesheet.getDownFrames());
		adown.setPlayMode(PlayMode.LOOP_PINGPONG);
		aleft = new Animation(FRAME_DURATION, spritesheet.getLeftFrames());
		aleft.setPlayMode(PlayMode.LOOP_PINGPONG);
		aright = new Animation(FRAME_DURATION, spritesheet.getRightFrames());
		aright.setPlayMode(PlayMode.LOOP_PINGPONG);
	}
	
	@Override
	public Rectangle getScreenRectangle() {
		boolean halfImageHeight = current == up || current == down || current == left || current == right;
		float heightscale = halfImageHeight ? 0.5f : 1;
		
		Vector2 screenPos = this.getScreenPosition();
		TextureRegion region = current.getKeyFrame(animationTimer.time());
		return new Rectangle(screenPos.x - current.getKeyFrames()[0].getRegionWidth() / 2, screenPos.y,
				region.getRegionWidth(), region.getRegionHeight() * heightscale);
	}
	
	private Animation up, down, left, right, aup, adown, aleft, aright, current;
	private Vector2 oldPos;
	private Stopwatch animationTimer;
	public float FRAME_DURATION = 0.2f; // in seconds
}
