package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.game.Building;
import com.aqua.ludum.ld30.game.ComputerPlayer;
import com.aqua.ludum.ld30.game.HumanPlayer;
import com.aqua.ludum.ld30.game.Player;
import com.aqua.ludum.ld30.game.Spirit;
import com.aqua.ludum.ld30.game.Temple;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Unit;
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
		
		Temple humanT = null, computerT = null;
		
		for(Unit u : terrain.getUnits()) {
			if(u instanceof Temple && u.getPlayer() == human) {
				humanT = (Temple) u;
			}
			if(u instanceof Temple && u.getPlayer() == computer) {
				computerT = (Temple) u;
			}
		}
		
		camera.translate(Constants.worldToScreen(humanT.getPosition(), terrain.getTilesHigh()));
		
		int SPIRITS = 15;
		for(int i = 0; i < SPIRITS; i ++) {
			terrain.addUnit(new Spirit(human, humanT.getPosition().cpy(), terrain));
			terrain.addUnit(new Spirit(computer, computerT.getPosition().cpy(), terrain));
		}
	}

	@Override
	public int getID() {
		return ID;
	}

}
