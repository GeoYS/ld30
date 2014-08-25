package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.util.Stopwatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class SpawnBuilding extends Building {

	public SpawnBuilding(Player player, Vector2 position, Terrain terrain, Texture buildingImage) {
		super(player, position, terrain);
		this.buildingImage = buildingImage;
		isBuilding = true;
		//buildingAnimation = new Animation(FRAME_DURATION, null);
		//buildingAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		animationTimer = new Stopwatch();
		animationTimer.start();
	}
	
	@Override
	public final void render(SpriteBatch batch) {
		if(isBuilding) {
			buildingAnimation.getKeyFrame(animationTimer.time() / 1000f);
		}
		else {
			renderBuilding(batch);
		}
	}
	
	@Override
	public void update(float delta) {
		if(animationTimer.time() / 1000f >= TIME_TO_COMPLETION) {
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
		batch.draw(buildingImage, screenPos.x - buildingImage.getWidth() / 2, screenPos.y - buildingImage.getHeight() / 2);
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
	
	protected abstract void handleKey(int key);
	
	private boolean isBuilding;
	private Animation buildingAnimation;
	private Stopwatch animationTimer;
	private Texture buildingImage;
	private float timeToSpawn = 0.0f;
	private boolean spawning = false;
	private final float FRAME_DURATION = 0.3f, TIME_TO_COMPLETION = 3; 
	public int spiritCount = 0;
}
