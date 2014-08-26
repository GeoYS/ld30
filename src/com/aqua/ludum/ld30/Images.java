package com.aqua.ludum.ld30;

import com.aqua.ludum.ld30.g2d.SpriteSheet;
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
	public static final Texture AWORKER_RIGHTLEFT, AWORKER_DOWNUP, ASOLDIER_RIGHTLEFT, ASOLDIER_DOWNUP,
		ATANK_RIGHTLEFT, ATANK_DOWNUP, AFAST_RIGHTLEFT, AFAST_DOWNUP, ARANGED_RIGHTLEFT, ARANGED_DOWNUP;
	public static final UnitSpritesheet WORKER_SPRITESHEET, TANK_SPRITESHEET, SOLDIER_SPRITESHEET, SPIRIT_SPRITESHEET,
		FAST_SPRITESHEET, RANGED_SPRITESHEET;

	public static UnitSpritesheet AWORKER_SPRITESHEET;

	public static UnitSpritesheet ATANK_SPRITESHEET;

	public static UnitSpritesheet ASOLDIER_SPRITESHEET;

	public static UnitSpritesheet AFAST_SPRITESHEET;

	public static UnitSpritesheet ARANGED_SPRITESHEET;
	
	public static SpriteSheet BUILDING = new SpriteSheet(new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tent_building_sprite.png"))), 136, 96);
	
	static {
		// buildings
		RANGED_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/ranged_tent_sprite.png")));
		NEUTRAL_BUILDING = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/neutral_tent_sprite.png")));
		SOLDIER_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/soldier_tent_sprite.png")));
		FAST_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/fast_tent_sprite.png")));
		TANK_TENT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tank_tent_sprite.png")));
		TEMPLE = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/temple_sprite.png")));
		
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
		WORKER_SPRITESHEET = new UnitSpritesheet(WORKER_LEFTUP, WORKER_DOWNRIGHT, false);
		TANK_SPRITESHEET = new UnitSpritesheet(TANK_LEFTUP, TANK_DOWNRIGHT, false);
		SOLDIER_SPRITESHEET = new UnitSpritesheet(SOLDIER_LEFTUP, SOLDIER_DOWNRIGHT, false);
		SPIRIT_SPRITESHEET = new UnitSpritesheet(SPIRIT_LEFTUP, SPIRIT_DOWNRIGHT, false);
		FAST_SPRITESHEET = new UnitSpritesheet(FAST_LEFTUP, FAST_DOWNRIGHT, false);
		RANGED_SPRITESHEET = new UnitSpritesheet(RANGED_LEFTUP, RANGED_DOWNRIGHT, false);		
		
		// ATTACKING UNITS
		// units
		AWORKER_RIGHTLEFT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/worker_attack_sprite_1.png")));
		AWORKER_DOWNUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/worker_attack_sprite_2.png")));
		ASOLDIER_RIGHTLEFT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/soldier_attack_sprite_1.png")));
		ASOLDIER_DOWNUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/soldier_attack_sprite_2.png")));
		ATANK_RIGHTLEFT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tank_attack_sprite_1.png")));
		ATANK_DOWNUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/tank_attack_sprite_2.png")));
		AFAST_RIGHTLEFT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/fast_attack_sprite_1.png")));
		AFAST_DOWNUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/fast_attack_sprite_2.png")));
		ARANGED_RIGHTLEFT = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/ranged_attack_sprite_1.png")));
		ARANGED_DOWNUP = new Texture(new Pixmap(Gdx.files.internal("../LD30/res/units/ranged_attack_sprite_2.png")));
		AWORKER_SPRITESHEET = new UnitSpritesheet(AWORKER_RIGHTLEFT, AWORKER_DOWNUP, true);
		ATANK_SPRITESHEET = new UnitSpritesheet(ATANK_RIGHTLEFT, ATANK_DOWNUP, true);
		ASOLDIER_SPRITESHEET = new UnitSpritesheet(ASOLDIER_RIGHTLEFT, ASOLDIER_DOWNUP, true);
		AFAST_SPRITESHEET = new UnitSpritesheet(AFAST_RIGHTLEFT, AFAST_DOWNUP, true);
		ARANGED_SPRITESHEET = new UnitSpritesheet(ARANGED_RIGHTLEFT, ARANGED_DOWNUP, true);	
	}
}
