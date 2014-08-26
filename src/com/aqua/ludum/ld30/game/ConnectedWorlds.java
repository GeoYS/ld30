package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.game.screens.test.LoseScreen;
import com.aqua.ludum.ld30.game.screens.test.TestScreen;
import com.aqua.ludum.ld30.game.screens.test.WinScreen;
import com.aqua.ludum.ld30.screen.Game;

public class ConnectedWorlds  extends Game{

	@Override
	public void create() {	
		this.addScreen(new TestScreen());
		this.addScreen(new WinScreen());
		this.addScreen(new LoseScreen());
		this.initScreens();
		this.enterScreen(TestScreen.ID);
	}

}
