package com.aqua.ludum.ld30;

import com.aqua.ludum.ld30.game.ConnectedWorlds;
import com.aqua.ludum.ld30.screen.Game;
import com.aqua.ludum.ld30.screen.GameApp;
import com.badlogic.gdx.graphics.FPSLogger;

public class MainGameClass extends GameApp {

	FPSLogger fps = new FPSLogger();
	
	@Override
	public Game game() {
		return new ConnectedWorlds();
	}
	
	@Override
	public void render() {
		super.render();
		fps.log();
	}
	
}
