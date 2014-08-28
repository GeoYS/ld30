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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

public class TestScreen extends GameScreen{

	public static int ID = 0;
	
	private Terrain terrain;
	private SpriteBatch batch, titleBatch;
	private OrthographicCamera camera;
	private Game game;
	private Temple humanT = null, computerT = null;
	
	private boolean title = true;
	private Music music;
	
	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();

		Gdx.gl20.glClearColor(0,0,0,0);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		terrain.render(batch);
		
		batch.end();
		
		if(title) {
			titleBatch.begin();
			titleBatch.draw(Images.TITLE,
					Gdx.graphics.getWidth() / 2 - Images.TITLE.getWidth() / 2,
					Gdx.graphics.getHeight() / 2 - Images.TITLE.getHeight() / 2);
			titleBatch.end();
		}
	}

	@Override
	public void update(float delta) {
		if(title) {
			return;
		}
		if(humanT != null && humanT.hp < 0) {
			music.stop();
			game.enterScreen(LoseScreen.ID);
		}
		if(computerT != null && computerT.hp < 0) {
			music.stop();
			game.enterScreen(WinScreen.ID);
		}
		terrain.update(delta);
	}

	@Override
	public void init(Game game) {
		this.clearProcessors();
		title = true;
		this.game = game;
		music = Gdx.audio.newMusic(Gdx.files.internal("../LD30/res/ld30music1.ogg"));
		music.play();
		batch = new SpriteBatch();
		titleBatch = new SpriteBatch();
		camera = new OrthographicCamera(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		HumanPlayer human = new HumanPlayer("George", camera);
		ComputerPlayer computer = new ComputerPlayer("AI");
		terrain = new Terrain("../LD30/res/html5map.tmx", camera, human, computer);
		human.setTerrain(terrain);
		computer.setTerrain(terrain);
		human.setInputListener();
		
		Temple humanT = null, computerT = null;
		
		for(Unit u : terrain.getUnits()) {
			if(u instanceof Temple && u.getPlayer() == human) {
				humanT = this.humanT = (Temple) u;
			}
			if(u instanceof Temple && u.getPlayer() == computer) {
				computerT = this.computerT = (Temple) u;
			}
		}
		
		camera.translate(Constants.worldToScreen(humanT.getPosition(), terrain.getTilesHigh()));
		
		int SPIRITS = 15;
		for(int i = 0; i < SPIRITS; i ++) {
			terrain.addUnit(new Spirit(human, humanT.getPosition().cpy(), terrain));
			terrain.addUnit(new Spirit(computer, computerT.getPosition().cpy(), terrain));
		}
		
		terrain.update(0.01f);
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		System.out.println(title);
		if(title) {
			title = false;
			System.out.println(terrain.getPlayers().size());
			for(Player player : terrain.getPlayers()) {
				if(player instanceof HumanPlayer) {
					this.addProcessor(((HumanPlayer) player).getListener());
				}
			}
			return false;
		}
		return super.touchUp(screenX, screenY, pointer, button);
	}

}
