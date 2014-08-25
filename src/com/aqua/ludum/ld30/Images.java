package com.aqua.ludum.ld30;

import com.aqua.ludum.ld30.game.UnitSpritesheet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Images {
	
	// buildings
	public static final Texture RANGED_TENT, NEUTRAL_BUILDING, SOLDIER_TENT, TANK_TENT, FAST_TENT, TEMPLE;

	// units
	public static final Texture WORKER_LEFTUP, WORKER_DOWNRIGHT, SOLDIER_LEFTUP, SOLDIER_DOWNRIGHT,
		TANK_LEFTUP, TANK_DOWNRIGHT, FAST_LEFTUP, FAST_DOWNRIGHT, RANGED_LEFTUP, RANGED_DOWNRIGHT,
		SPIRIT_LEFTUP, SPIRIT_DOWNRIGHT;
	public static final UnitSpritesheet WORKER_SPRITESHEET, TANK_SPRITESHEET, SOLDIER_SPRITESHEET, SPIRIT_SPRITESHEET,
		FAST_SPRITESHEET, RANGED_SPRITESHEET;
	
	static {
		// buildings
		RANGED_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/ranged_tent_sprite.png")));
		NEUTRAL_BUILDING = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/igloo.png")));
		SOLDIER_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/soldier_tent_sprite.png")));
		FAST_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/fast_tent_sprite.png")));
		TANK_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tank_tent_sprite.png")));
		TEMPLE = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/temple.png")));
		
		// units
		WORKER_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/worker_sprite_2.png")));
		WORKER_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/worker_sprite_1.png")));
		SOLDIER_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/soldier_sprite_2.png")));
		SOLDIER_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/soldier_sprite_1.png")));
		SPIRIT_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/spirit_sprite_2.png")));
		SPIRIT_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/spirit_sprite_1.png")));
		TANK_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tank_sprite_2.png")));
		TANK_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tank_sprite_1.png")));
		FAST_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/fast_sprite_2.png")));
		FAST_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/fast_sprite_1.png")));
		RANGED_LEFTUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/ranged_sprite_2.png")));
		RANGED_DOWNRIGHT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/ranged_sprite_1.png")));
		WORKER_SPRITESHEET = new UnitSpritesheet(WORKER_LEFTUP, WORKER_DOWNRIGHT);
		TANK_SPRITESHEET = new UnitSpritesheet(TANK_LEFTUP, TANK_DOWNRIGHT);
		SOLDIER_SPRITESHEET = new UnitSpritesheet(SOLDIER_LEFTUP, SOLDIER_DOWNRIGHT);
		SPIRIT_SPRITESHEET = new UnitSpritesheet(SPIRIT_LEFTUP, SPIRIT_DOWNRIGHT);
		FAST_SPRITESHEET = new UnitSpritesheet(FAST_LEFTUP, FAST_DOWNRIGHT);
		RANGED_SPRITESHEET = new UnitSpritesheet(RANGED_LEFTUP, RANGED_DOWNRIGHT);		
	}
}
