package com.aqua.ludum.ld30.game;

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
		this.shoved = false;
	}
	
	public abstract void render(SpriteBatch batch);
	public abstract float getSpeed();
	public abstract float getRadius();
	public abstract float getAttackRadius();
	public abstract float getAttackStrength();
	public abstract float getMeleeArmour();
	public abstract float getRangeArmour();
	
	public void update(float delta) {
		collisionUpdate(delta);
		pathingUpdate(delta);
	}
	
	protected final void collisionUpdate(float delta) {
		this.handleWallCollision();
		if (!currentPath.getPoints().isEmpty()) {
			for (Unit unit : terrain.getUnits()) {
				if (unit.currentPath.getPoints().isEmpty() && !unit.shoved && this != unit) {
					if (unit.position.dst2(position) <= (unit.radius + radius) * (unit.radius + radius)) {
						unit.shoved = true;
						Vector2 displacement = unit.position.cpy().sub(position).nor().scl(unit.radius + radius + Constants.COLLISION_PADDING);
						unit.position.set(displacement.add(position));
					}
				}
			}
		}
		else if (shoved) {
			shoved = false;
			for (Unit unit : terrain.getUnits()) {
				if (unit.currentPath.getPoints().isEmpty() && !unit.shoved && this != unit) {
					if (unit.position.dst2(position) <= (unit.radius + radius) * (unit.radius + radius)) {
						unit.shoved = true;
						Vector2 displacement = unit.position.cpy().sub(position).nor().scl(unit.radius + radius + Constants.COLLISION_PADDING);
						unit.position.set(displacement.add(position));
					}
				}
			}
		}
	}
	
	private void handleWallCollision() {
		for (Block block : terrain.getBlocks()) {
			Rectangle rect = block.getRectangle();
			Vector2 upperLeft = new Vector2(rect.x, rect.y);
			Vector2 upperRight = new Vector2(rect.x + rect.width, rect.y);
			Vector2 lowerLeft = new Vector2(rect.x, rect.y + rect.height);
			Vector2 lowerRight = new Vector2(rect.x + rect.width, rect.y + rect.height);
			Vector2 point = new Vector2();
			Vector2 direction = null;
			Intersector.nearestSegmentPoint(upperLeft, upperRight, position, point);
			if (point.dst2(position) <= radius * radius) {
				if (Intersector.pointLineSide(lowerLeft, upperLeft, position) == -1) {
					direction = new Vector2(-1, -1).nor();
				}
				else if (Intersector.pointLineSide(lowerRight, upperRight, position) == 1) {
					direction = new Vector2(1, -1).nor();
				}
				else {
					direction = new Vector2(0, -1);
				}
			}
			if (direction == null) {
				Intersector.nearestSegmentPoint(upperLeft, lowerLeft, position, point);
				if (point.dst2(position) <= radius * radius) {
					if (Intersector.pointLineSide(upperLeft, upperRight, position) == -1) {
						direction = new Vector2(-1, -1).nor();
					}
					else if (Intersector.pointLineSide(lowerLeft, lowerRight, position) == 1) {
						direction = new Vector2(-1, 1).nor();
					}
					else {
						direction = new Vector2(-1, 0);
					}
				}
			}
			if (direction == null) {
				Intersector.nearestSegmentPoint(upperRight, lowerRight, position, point);
				if (point.dst2(position) <= radius * radius) {
					if (Intersector.pointLineSide(upperLeft, upperRight, position) == -1) {
						direction = new Vector2(1, -1).nor();
					}
					else if (Intersector.pointLineSide(lowerLeft, lowerRight, position) == 1) {
						direction = new Vector2(1, 1).nor();
					}
					else {
						direction = new Vector2(1, 0);
					}
				}
			}
			if (direction == null) {
				Intersector.nearestSegmentPoint(lowerLeft, lowerRight, position, point);
				if (point.dst2(position) <= radius * radius) {
					if (Intersector.pointLineSide(lowerLeft, upperLeft, position) == -1) {
						direction = new Vector2(-1, 1).nor();
					}
					else if (Intersector.pointLineSide(lowerRight, upperRight, position) == 1) {
						direction = new Vector2(1, 1).nor();
					}
					else {
						direction = new Vector2(0, 1);
					}
				}
			}
			if(direction != null) {
				position.add(direction.scl(radius + Constants.COLLISION_PADDING));
			}
		}
	}
	
	protected final void attack(float delta) {
		
	}
	
	protected final int getHP() {
		return this.hp;
	}
	
	protected final void pathingUpdate(float delta) {
		if (this.targetUnit != null) {
			if (this.position.dst2(this.targetUnit.position) > getAttackRadius() * getAttackRadius()) {
				currentPath = Path.shortestPath(terrain, this.position, this.targetUnit.position);
				if (currentPath == null) {
					currentPath = new Path();
				}
			}
			else {
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
		final float MIN_DIST_SQUARED = (this.getRadius() + DIST_BUFFER) * (this.getRadius() + DIST_BUFFER); // distance considered "at the next path point"
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
	
	public final void commandMove(Vector2 to) {
		this.currentPath = Path.shortestPath(terrain, this.position, to);
		this.targetUnit = null;
		if (currentPath == null) {
			currentPath = new Path();
		}
	}
	
	public final void commandAttack(Unit target) {
		this.targetUnit = target;
		this.currentPath = Path.shortestPath(terrain, this.position, target.position);
		if (currentPath == null) {
			currentPath = new Path();
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
	
	public final Circle getCollisionShape() {
		return new Circle(this.position, this.radius);
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	private Player player;
	private Vector2 position;
	private Unit targetUnit;
	private Stance stance;
	private float radius;
	private Terrain terrain;
	private Path currentPath;
	private int hp;
	private boolean shoved;
	
}
