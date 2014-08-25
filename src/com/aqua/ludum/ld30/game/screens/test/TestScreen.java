package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.game.ComputerPlayer;
import com.aqua.ludum.ld30.game.HumanPlayer;
import com.aqua.ludum.ld30.game.Player;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Worker;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameScreen;
import com.badlogic.gdx.Gdx;
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
		HumanPlayer human = new HumanPlayer("George", camera);
		ComputerPlayer computer = new ComputerPlayer("AI");
		terrain = new Terrain("../LD30/res/LudumDareMap01.tmx", camera, human, computer);
		human.setTerrain(terrain);
		computer.setTerrain(terrain);
		human.setInputListener();
		for(Player player : terrain.getPlayers()) {
			if(player instanceof HumanPlayer) {
				this.addProcessor(((HumanPlayer) player).getListener());
			}
		}
		terrain.addUnit(new Worker(computer, new Vector2(0, 0), terrain));
		terrain.addUnit(new Worker(computer, new Vector2(20, 0), terrain));
		terrain.addUnit(new Worker(computer, new Vector2(40, 0), terrain));
		terrain.addUnit(new Worker(computer, new Vector2(60, 0), terrain));
	}

	@Override
	public int getID() {
		return ID;
	}

}
