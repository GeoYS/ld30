package com.aqua.ludum.ld30.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;

public final class Path {
	
	public Path() {
		this.points = new ArrayList<>();
	}
	
	public static Path shortestPath(Terrain terrain, Vector2 start, Vector2 goal) {
		Set<Vector2> closedSet = new HashSet<>();
		Set<Vector2> openSet = new HashSet<>();
		openSet.add(start);
		Map<Vector2, Vector2> cameFrom = new HashMap<>();
		Map<Vector2, Float> gScore = new HashMap<>();
		Map<Vector2, Float> fScore = new HashMap<>();
		gScore.put(start, 0.0f);
		fScore.put(start, goal.dst(start));
		
		while (!openSet.isEmpty()) {
			Vector2 current = null;
			float lowestFScore = Float.POSITIVE_INFINITY;
			for (Vector2 node : openSet) {
				float f = fScore.get(node);
				if (f < lowestFScore) {
					lowestFScore = f;
					current = node;
				}
			}
			if (current.equals(goal)) {
				Path result = reconstructPath(cameFrom, goal);
				result.points.remove(0);
				return result;
			}
			openSet.remove(current);
			closedSet.add(current);
			for (Vector2 neighbour : terrain.getNeighbours(current, goal)) {
				if (closedSet.contains(neighbour)) {
					continue;
				}
				float tentativeGScore = gScore.get(current) + current.dst(neighbour);
				if (!openSet.contains(neighbour) || tentativeGScore < gScore.get(neighbour)) {
					cameFrom.put(neighbour, current);
					gScore.put(neighbour, tentativeGScore);
					fScore.put(neighbour, tentativeGScore + neighbour.dst(goal));
					if (!openSet.contains(neighbour)) {
						openSet.add(neighbour);
					}
				}
			}
		}
		return null;
	}
	
	private static Path reconstructPath(Map<Vector2, Vector2> cameFrom, Vector2 current) {
		if (cameFrom.containsKey(current)) {
			Path p = reconstructPath(cameFrom, cameFrom.get(current));
			p.points.add(current);
			return p;
		}
		else {
			Path p = new Path();
			p.points.add(current);
			return p;
		}
	}
	
	public List<Vector2> getPoints() {
		return this.points;
	}
	
	private List<Vector2> points;
	
}
