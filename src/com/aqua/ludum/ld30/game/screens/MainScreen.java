package com.aqua.ludum.ld30.game.screens;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen extends GameScreen{

	public static int ID = 0;
	
	private Terrain terrain;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	@Override
	public void render() {
		batch.begin();
		
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
		terrain = new Terrain("../LD30/res/", camera);
	}

	@Override
	public int getID() {
		return ID;
	}

}
