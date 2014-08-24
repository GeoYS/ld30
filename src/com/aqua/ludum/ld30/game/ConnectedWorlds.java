package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.game.screens.test.TestScreen;
import com.aqua.ludum.ld30.screen.Game;

public class ConnectedWorlds  extends Game{

	@Override
	public void create() {	
		this.addScreen(new TestScreen());
		this.initScreens();
		this.enterScreen(TestScreen.ID);
	}

}
