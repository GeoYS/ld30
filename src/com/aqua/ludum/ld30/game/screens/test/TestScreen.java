package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Worker;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestScreen extends GameScreen{

	public static int ID = 0;
	
	private Terrain terrain;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private int lastX, lastY;
	
	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();

		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		terrain.render(batch);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
		terrain.update(delta);
	}

	@Override
	public void init(Game game) {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		terrain = new TestTerrain(camera);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		lastX = screenX;
		lastY = screenY;
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		camera.translate(-(screenX - lastX), screenY - lastY);
		camera.update();
		lastX = screenX;
		lastY = screenY;
		return super.touchDragged(screenX, screenY, pointer);
	}
	
	@Override
	public int getID() {
		return ID;
	}

}
