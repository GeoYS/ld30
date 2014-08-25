package com.aqua.ludum.ld30.game;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Vector2;

public class Spirit extends AnimatedUnit {
	
	public Spirit(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SPIRIT_SPRITESHEET);
		targetBuilding = null;
	}
	
	@Override
	public void attack(float delta) {
		getTargetUnit().shp -= delta * 30;
		if (getTargetUnit().shp < 0) {
			getTargetUnit().shp = getTargetUnit().getStartSHP();
			getTargetUnit().setPlayer(getPlayer());
			hp = -10;
		}
	}
	
	@Override
	protected void pathingUpdate(float delta) {
		if (this.targetBuilding != null) {
			if (this.targetBuilding.player != player) {
				this.targetBuilding = null;
			}
			else if (this.position.dst2(this.targetBuilding.position) > (targetBuilding.getRadius() + getRadius()) * (targetBuilding.getAttackRadius() + getRadius())) {
				move(new Vector2(1.0f, 1.0f).nor().scl(targetBuilding.getRadius() + Constants.PATHFINDING_CORNER_PADDING).add(targetBuilding.position));
			}
			else {
				stopMove();
				hp = -10;
				targetBuilding.spiritCount += 1;
			}
		}
		super.pathingUpdate(delta);
	}
	
	public boolean commandTargetBuilding(SpawnBuilding building) {
		this.targetBuilding = building;
		this.targetUnit = null;
		if (move(building.position)) {
			return true;
		}
		else {
			this.targetBuilding = null;
			return false;
		}
	}
	
	@Override
	public boolean commandAttack(Unit target) {
		this.targetBuilding = null;
		return super.commandAttack(target);
	}

	@Override
	public float getSpeed() {
		return 50.0f;
	}

	@Override
	public float getRadius() {
		return 12.0f;
	}

	@Override
	public float getAttackRadius() {
		return 32.0f;
	}

	@Override
	public float getAttackStrength() {
		return 0;
	}

	@Override
	public float getMeleeArmour() {
		return Float.POSITIVE_INFINITY;
	}

	@Override
	public float getRangeArmour() {
		return Float.POSITIVE_INFINITY;
	}

	@Override
	public float getStartHP() {
		return Float.POSITIVE_INFINITY;
	}
	
	@Override
	public float getStartSHP() {
		return Float.POSITIVE_INFINITY;
	}
	
	private SpawnBuilding targetBuilding;
	
}
