package com.aqua.ludum.ld30;

import com.aqua.ludum.ld30.game.UnitSpritesheet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Images {

	public static final Texture WORKER_LEFTUP, WORKER_DOWNRIGHT;
	public static final UnitSpritesheet WORKER_SPRITESHEET;
	
	static {
		WORKER_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/worker_sprite_1.png")));
		WORKER_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/worker_sprite_2.png")));
		WORKER_SPRITESHEET = new UnitSpritesheet(WORKER_LEFTUP, WORKER_DOWNRIGHT);
	}
}
