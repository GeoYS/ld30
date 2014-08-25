package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.List;

import com.aqua.ludum.ld30.Constants;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Unit {
	
	public Unit(Player player, Vector2 position, Terrain terrain) {
		this.player = player;
		this.terrain = terrain;
		this.position = position;
		this.currentPath = new Path();
		this.hp = getStartHP();
		this.shp = getStartSHP();
		//this.shoved = false;
	}
	
	public abstract void render(SpriteBatch batch);
	public abstract float getSpeed();
	public abstract float getRadius();
	public abstract float getStartHP();
	public abstract float getStartSHP();
	public abstract float getAttackRadius();
	public abstract float getAttackStrength();
	public abstract float getMeleeArmour();
	public abstract float getRangeArmour();
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	public void update(float delta) {
		stanceUpdate(delta);
		collisionUpdate(delta);
		pathingUpdate(delta);
		if (hp > 0) {
			hp += delta * 1;
			shp += delta * 10;
			if (hp > getStartHP()) {
				hp = getStartHP();
			}
			if (shp > getStartSHP()) {
				shp = getStartSHP();
			}
		}
	}
	
	protected void collisionUpdate(float delta) {
		this.handleWallCollision();
	}
	
	private void handleWallCollision() {
		for (Block block : terrain.getBlocks()) {
			Rectangle rect = block.getRectangle();
			Vector2 collisionPoint = getCollisionPoint(rect);
			if (collisionPoint != null) {
				Vector2 r = position.cpy().sub(collisionPoint);
				position.add(r.cpy().nor().scl(getRadius() - r.len() + Constants.COLLISION_PADDING));
			}
		}
		for (Unit unit : terrain.getUnits()) {
			if (!(unit instanceof Building)) {
				continue;
			}
			Rectangle rect = ((Building) unit).getRectangle();
			Vector2 collisionPoint = getCollisionPoint(rect);
			if (collisionPoint != null) {
				Vector2 r = position.cpy().sub(collisionPoint);
				position.add(r.cpy().nor().scl(getRadius() - r.len() + Constants.COLLISION_PADDING));
			}
		}
	}
	
	private Vector2 getCollisionPoint(Rectangle rect) {
		Vector2 upperLeft = new Vector2(rect.x, rect.y);
		Vector2 upperRight = new Vector2(rect.x + rect.width, rect.y);
		Vector2 lowerLeft = new Vector2(rect.x, rect.y + rect.height);
		Vector2 lowerRight = new Vector2(rect.x + rect.width, rect.y + rect.height);
		Vector2 point = new Vector2();
		Intersector.nearestSegmentPoint(upperLeft, upperRight, position, point);
		if (point.dst2(position) > getRadius() * getRadius()) {
			Intersector.nearestSegmentPoint(upperLeft, lowerLeft, position, point);
		}
		if (point.dst2(position) > getRadius() * getRadius()) {
			Intersector.nearestSegmentPoint(upperRight, lowerRight, position, point);
		}
		if (point.dst2(position) > getRadius() * getRadius()) {
			Intersector.nearestSegmentPoint(lowerLeft, lowerRight, position, point);
		}
		if (point.dst2(position) <= getRadius() * getRadius()) {
			return point;
		}
		else {
			return null;
		}
	}
	
	/*private void handleUnitCollision() {
		if (!currentPath.getPoints().isEmpty() || shoved) {
			shoved = false;
			for (Unit unit : terrain.getUnits()) {
				if (this != unit) {
					float radii2 = unit.getRadius() + getRadius();
					radii2 *= radii2;
					if (unit.position.dst2(position) <= radii2) {
						unit.shoved = true;
						Vector2 displacement = unit.position.cpy().sub(position).nor().scl(unit.getRadius() + getRadius() - unit.position.dst(position) + Constants.COLLISION_PADDING).scl(0.5f);
						unit.position.add(displacement);
						position.sub(displacement);
						if (!currentPath.getPoints().isEmpty()) {
							currentPath = Path.shortestPath(terrain, position, currentPath.getPoints().get(currentPath.getPoints().size() - 1));
						}
						if (!unit.currentPath.getPoints().isEmpty()) {
							unit.currentPath = Path.shortestPath(terrain, unit.position, unit.currentPath.getPoints().get(unit.currentPath.getPoints().size() - 1));
						}
					}
				}
			}
		}
	}*/
	
	protected void attack(float delta) {
		targetUnit.hp -= getAttackStrength() * delta * (100 / (100 + targetUnit.getMeleeArmour()));
		if (targetUnit.hp < 0) {
			targetUnit = null;
		}
	}
	
	protected final float getHP() {
		return this.hp;
	}
	
	protected final float getSHP() {
		return this.shp;
	}
	
	protected final void stanceUpdate(float delta) {
		if (stance == Stance.StandGround) {
			for (Unit unit : terrain.getUnits()) {
				if (unit.targetUnit == this) {
					commandAttack(unit);
					break;
				}
			}
		}
		if (stance == Stance.Aggressive) {
			for (Unit unit : terrain.getUnits()) {
				if (unit.player != player && unit.player != terrain.getNeutralPlayer() && unit.position.dst2(position) <= 128 * 128) {
					commandAttack(unit);
					break;
				}
			}
		}
	}
	
	protected void pathingUpdate(float delta) {
		isAttacking = false;
		if (this.targetUnit != null) {
			if (this.targetUnit.hp < 0 || this.targetUnit.player == player) {
				this.targetUnit = null;
			}
			else if (this.position.dst2(this.targetUnit.position) > getAttackRadius() * getAttackRadius() ||
					(!isAttacking && this.position.dst2(this.targetUnit.position) > (getAttackRadius() + 8) * (getAttackRadius() + 8))) {
				move(targetUnit.position);
			}
			else {
				isAttacking = true;
				stopMove();
				attack(delta);
			}
		}
		// path empty
		if(currentPath.getPoints().isEmpty()) {
			return;
		}
		// update position
		Vector2 target = currentPath.getPoints().get(0),
				deltaPos;
		deltaPos = target.cpy().sub(position).nor().scl(getSpeed() * delta);
		position.add(deltaPos);
		// check if next path point reached
		final float DIST_BUFFER = 1; // unit does not reach precisely at the target
		final float MIN_DIST_SQUARED = DIST_BUFFER * DIST_BUFFER; // distance considered "at the next path point"
		float distSquared = target.dst2(position);
		if(distSquared <= MIN_DIST_SQUARED) {
			// update path
			currentPath.getPoints().remove(0);
		}
	}
	
	protected final Unit getTargetUnit() {
		return this.targetUnit;
	}
	
	public final int getDepth() {
		return 0;
	}
	
	protected void stopMove() {
		currentPath = new Path();
	}
	
	protected boolean move(Vector2 to) {
		return move(to, new ArrayList<Unit>());
	}
	
	protected boolean move(Vector2 to, List<Unit> ignore) {
		/*for (Unit unit : terrain.getUnits()) {
			if (ignore.contains(unit) || unit == this || unit.getPlayer() != getPlayer()) {
				continue;
			}
			/*if (!(unit instanceof Building)) {
				Vector2 otherTo = null;
				if (unit.currentPath.getPoints().isEmpty()) {
					otherTo = unit.getPosition();
				}
				else {
					otherTo = unit.currentPath.getPoints().get(unit.currentPath.getPoints().size() - 1);
				}
				if (to.dst2(otherTo) <= (unit.getRadius() + getRadius()) * (unit.getRadius() + getRadius())) {
					currentPath = new Path();
					return false;
				}
			}
		}*/
		
		this.currentPath = Path.shortestPath(terrain, this.position, to);
		if (currentPath == null) {
			currentPath = new Path();
			return false;
		}
		return true;
	}
	
	public final boolean commandMove(Vector2 to, List<Unit> ignore) {
		this.targetUnit = null;
		return move(to, ignore);
	}
	
	public boolean commandAttack(Unit target) {
		this.targetUnit = target;
		if (move(target.position)) {
			return true;
		}
		else {
			this.targetUnit = null;
			return false;
		}
	}
	
	public final Stance getStance() {
		return this.stance;
	}
	
	public final void setStance(Stance stance) {
		this.stance = stance;
	}
	
	public final Player getPlayer() {
		return this.player;
	}
	
	public final void setPlayer(Player player) {
		this.player = player;
	}
	
	public final Vector2 getPosition() {
		return this.position;
	}
	
	public final Vector2 getScreenPosition() {
		return Constants.worldToScreen(this.getPosition(), getTerrain().getTilesHigh());
	}
	
	public final Circle getCollisionShape() {
		return new Circle(this.position, this.getRadius());
	}
	
	public Path getPath() {
		return currentPath;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	protected Player player;
	protected Vector2 position;
	protected Unit targetUnit;
	protected Stance stance;
	protected Terrain terrain;
	protected Path currentPath;
	protected float hp;
	protected float shp;
	private boolean isAttacking = false;
	//private boolean shoved;
	
}
