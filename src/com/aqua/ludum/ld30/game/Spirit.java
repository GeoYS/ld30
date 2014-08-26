package com.aqua.ludum.ld30.game;

import java.util.List;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.Images;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Spirit extends AnimatedUnit {
	
	public Spirit(Player player, Vector2 position, Terrain terrain) {
		super(player, position, terrain, Images.SPIRIT_SPRITESHEET, Images.SPIRIT_SPRITESHEET);
		targetBuilding = null;
	}
	
	@Override
	public void attack(float delta) {
		getTargetUnit().shp -= delta * 30;
		if (getTargetUnit().shp < 0) {
			getTargetUnit().shp = getTargetUnit().getStartSHP();
			if (getTargetUnit().getPlayer() != terrain.getNeutralPlayer() &&
					getTargetUnit().getPlayer() != player) {
				terrain.spawnUnit(new Spirit(getTargetUnit().player, getTargetUnit().position.cpy(), terrain));
			}
			getTargetUnit().setPlayer(getPlayer());
			hp = -10;
		}
	}
	
	@Override
	protected void collisionUpdate(float delta) {
		
	}
	
	@Override
	protected boolean move(Vector2 to, List<Unit> ignore) {
		this.currentPath = new Path();
		if (to.x < 0 || to.y < 0 || to.x > terrain.getTilesWide() * 32.0f || to.y > terrain.getTilesHigh() * 32.0f) {
			return false;
		}
		currentPath.getPoints().add(to);
		return true;
	}
	
	@Override
	protected void pathingUpdate(float delta) {
		if (this.targetBuilding != null) {
			if (this.targetBuilding.player != player) {
				this.targetBuilding = null;
			}
			else if (this.position.dst2(this.targetBuilding.position) > (targetBuilding.getRadius() + getRadius() + 64.0f) * (targetBuilding.getAttackRadius() + getRadius() + 64.0f)) {
				move(targetBuilding.position.cpy().sub(targetBuilding.position.cpy().sub(position).nor().scl(targetBuilding.getRadius() + getRadius() + 4.0f)));
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
		if (move(targetBuilding.position.cpy().sub(targetBuilding.position.cpy().sub(position).nor().scl(targetBuilding.getRadius() + getRadius() + 4.0f)))) {
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
	
	public SpawnBuilding targetBuilding;
	
}
