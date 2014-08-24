package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.game.HumanPlayer;
import com.aqua.ludum.ld30.game.Player;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Worker;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TestScreen extends GameScreen{

	public static int ID = 0;
	
	private Terrain terrain;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
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
		for(Player player : terrain.getPlayers()) {
			if(player instanceof HumanPlayer) {
				this.addProcessor(((HumanPlayer) player).getListener());
			}
		}
		System.out.println((new Vector2(1.0f, 1.0f)).equals(new Vector2(1.0f, 1.0f)));
	}

	@Override
	public int getID() {
		return ID;
	}

}
