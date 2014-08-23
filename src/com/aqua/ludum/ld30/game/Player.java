package com.aqua.ludum.ld30.game;

import com.badlogic.gdx.InputProcessor;

public abstract class Player {
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract void update(float delta);
	
	public InputProcessor getListener() {
		return null;
	}
	
	private String name;
	
}
