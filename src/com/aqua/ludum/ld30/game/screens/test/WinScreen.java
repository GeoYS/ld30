package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinScreen extends GameScreen {

	public static int ID = 2;
	
	private SpriteBatch batch;
	private Game game;
	
	@Override
	public void render() {
		batch.begin();
		
		batch.draw(Images.WIN_IMAGE,
				Gdx.graphics.getWidth() / 2 - Images.WIN_IMAGE.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - Images.WIN_IMAGE.getHeight() / 2);
		
		batch.end();
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void init(Game game) {
		batch = new SpriteBatch();
		this.game = game;
	}

	@Override
	public int getID() {
		return ID;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		game.initScreens();
		game.enterScreen(TestScreen.ID);
		return super.touchUp(screenX, screenY, pointer, button);
	}

}
