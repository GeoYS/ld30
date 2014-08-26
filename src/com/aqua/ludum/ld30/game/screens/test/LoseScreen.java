package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Images;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LoseScreen extends GameScreen{

	public static int ID = 1;

	private SpriteBatch batch;
	
	@Override
	public void render() {
		batch.begin();
		
		batch.draw(Images.LOST_IMAGE,
				Gdx.graphics.getWidth() / 2 - Images.LOST_IMAGE.getWidth() / 2,
				Gdx.graphics.getHeight() / 2 - Images.LOST_IMAGE.getHeight() / 2);
		
		batch.end();
	}
	
	@Override
	public void update(float delta) {
	}

	@Override
	public void init(Game game) {
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
