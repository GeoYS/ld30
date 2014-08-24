package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.game.HumanPlayer;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Worker;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class TestTerrain extends Terrain {

	public TestTerrain(OrthographicCamera camera) {
		super("../LD30/res/LudumDareMap02.tmx", camera);
		this.getPlayers().add(new HumanPlayer("George", this, camera));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(0, 0), this));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(64, 0), this));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(128, 0), this));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(64, 64), this));
	}

}
