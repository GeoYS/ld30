package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

public class Worker extends AnimatedUnit {

	public Worker(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.WORKER_SPRITESHEET, Images.AWORKER_SPRITESHEET);
		isSpawning = false;
	}

	/*@Override
	public void render(SpriteBatch batch) {
		Vector2 screenPos = Constants.worldToScreen(this.getPosition(), getTerrain().getTilesHigh());
		batch.draw(Images.WORKER_IMAGE, screenPos.x, screenPos.y);
	}*/

	public void handleKey(int key) {
		if(key == Input.Keys.NUM_1) {
			becomeBuilding(new SoldierSpawn(this.getPlayer(), this.getPosition(), this.getTerrain()));
		}
		else if(key == Input.Keys.NUM_2) {
			becomeBuilding(new RangeSpawn(this.getPlayer(), this.getPosition(), this.getTerrain()));
		}
		else if(key == Input.Keys.NUM_3) {
			becomeBuilding(new FastSpawn(this.getPlayer(), this.getPosition(), this.getTerrain()));
		}
		else if(key == Input.Keys.NUM_4) {
			becomeBuilding(new TankSpawn(this.getPlayer(), this.getPosition(), this.getTerrain()));
		}
	}
	
	public void becomeBuilding(SpawnBuilding building) {
		for(Unit unit : getTerrain().getUnits()) {
			if(unit == this || unit instanceof Spirit) {
				continue;
			}
			float dst2 = unit.getPosition().dst2(building.getPosition()), mindst2;
			float buffer = 0;
			mindst2 = (building.getRadius() + buffer) * (unit.getRadius() + building.getRadius() + buffer);
			if(dst2 <  mindst2) {
				return;
			}
		}
		for(Block block : getTerrain().getBlocks()) {
			if(block.getRectangle().overlaps(building.getRectangle())) {
				return;
			}
		}
		terrain.spawnUnit(building);
		this.hp = -1;
		isSpawning = true;
	}
	
	@Override
	public float getSpeed() {
		return 40.0f;
	}

	@Override
	public float getRadius() {
		return 12.0f;
	}

	@Override
	public float getAttackRadius() {
		return 24.0f;
	}

	@Override
	public float getAttackStrength() {
		return 60.0f;
	}

	@Override
	public float getMeleeArmour() {
		return 0.0f;
	}

	@Override
	public float getRangeArmour() {
		return 0.0f;
	}

	@Override
	public float getStartHP() {
		return 120.0f;
	}

	@Override
	public float getStartSHP() {
		return 150.0f;
	}
	
	public Building toBuild;
	public boolean isSpawning;
}
