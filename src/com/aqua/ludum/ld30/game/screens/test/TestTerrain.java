package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Worker;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class TestTerrain extends Terrain {

	public TestTerrain(OrthographicCamera camera) {
		super("../LD30/res/lundum dare 2014.tmx", camera);
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(0, 0), this));
	}

}
