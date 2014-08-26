package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.util.Stopwatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class SpawnBuilding extends Building {

	public SpawnBuilding(Player player, Vector2 position, Terrain terrain, Texture buildingImage) {
		super(player, position, terrain);
		this.buildingImage = buildingImage;
		isBuilding = true;
		buildingAnimation = new Animation(FRAME_DURATION, Images.BUILDING.getFrames());
		buildingAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		animationTimer = new Stopwatch();
		animationTimer.start();
		this.spiritCount = 1;
	}
	
	@Override
	public final void render(SpriteBatch batch) {
		if(isBuilding) {
			Vector2 screenPos = this.getScreenPosition();
			TextureRegion frame = buildingAnimation.getKeyFrame(animationTimer.time() / 1000f);
			batch.draw(buildingAnimation.getKeyFrame(animationTimer.time() / 1000f), screenPos.x - frame.getRegionWidth() / 2, screenPos.y - 25);
		}
		else {
			renderBuilding(batch);
		}
	}
	
	@Override
	public void update(float delta) {
		if(animationTimer.time() / 1000f >= timeToCompletion()) {
			isBuilding = false;
		}
		if (!isBuilding) {
			if (this.spiritCount > 0 && !spawning) {
				spawning = true;
				this.timeToSpawn = this.timeUntilSpawn();
			}
			if (spawning) {
				timeToSpawn -= delta;
				if (timeToSpawn < 0) {
					spawning = false;
					spawn();
				}
			}
		}
	}
	
	public void spawn() {
		spiritCount -= 1;
	}
	
	public abstract float timeUntilSpawn();
	
	public final void renderBuilding(SpriteBatch batch) {
		Vector2 screenPos = this.getScreenPosition();
		batch.draw(buildingImage, screenPos.x - buildingImage.getWidth() / 2, screenPos.y - buildingImage.getHeight() / 2 + 25);
	}
	
	public void handleKeyDown(int key) {
		if(!isBuilding) {
			handleKey(key);
		}
	}
	
	@Override
	public float getStartHP() {
		return 1000.0f;
	}
	
	public abstract float timeToCompletion();
	
	protected abstract void handleKey(int key);
	
	@Override
	public Rectangle getScreenRectangle() {
		Vector2 screenPos = this.getScreenPosition();
		return new Rectangle(screenPos.x - buildingImage.getWidth() / 2, screenPos.y - buildingImage.getHeight() / 2 + 25,
				buildingImage.getWidth(), buildingImage.getHeight());
	}
	
	private boolean isBuilding;
	private Animation buildingAnimation;
	private Stopwatch animationTimer;
	private Texture buildingImage;
	public float timeToSpawn = 0.0f;
	protected boolean spawning = false;
	private float FRAME_DURATION = timeToCompletion() / 2;
	public int spiritCount = 0;
}
