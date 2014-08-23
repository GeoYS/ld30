package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.game.screens.MainScreen;
import com.aqua.ludum.ld30.screen.Game;

public class ConnectedWorlds  extends Game{

	@Override
	public void create() {	
		this.addScreen(new MainScreen());
		this.initScreens();
		this.enterScreen(MainScreen.ID);
	}

}
