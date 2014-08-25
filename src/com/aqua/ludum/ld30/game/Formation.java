package com.aqua.ludum.ld30.game;

import java.util.HashSet;
import java.util.Set;

import com.aqua.ludum.ld30.Pair;
import com.badlogic.gdx.math.Vector2;

public class Formation {
	
	public Formation(float spacing) {
		this.layer = -1;
		this.ringPos = -1;
		this.spacing = spacing;
		this.filled = new HashSet<>();
		this.emptyLayer = true;
	}
	
	public Vector2 getNextPosition() {
		if (layer == 0) {
			lastLayer = 0;
			lastRingPos = 0;
			layer = 1;
			ringPos = 0;
			return new Vector2(0.0f, 0.0f);
		}
		float angle = (float) (2.0 * Math.PI / (6.0 * layer));
		int coprime = layer < COPRIMES.length ? COPRIMES[layer] : 1;
		Vector2 next = new Vector2(
				spacing * (float) Math.cos(angle * ringPos * coprime) * layer,
				spacing * (float) Math.sin(angle * ringPos * coprime) * layer);
		boolean success = true;
		int segment = ringPos / (6 * layer);
		int localPos = ringPos - segment * layer;
		if (localPos == 0) {
			if (!filled.contains(new Pair<Integer, Integer>(layer - 1, segment * (layer - 1)))) {
				success = false;
			}
		}
		else {
			if (!filled.contains(new Pair<Integer, Integer>(layer - 1, segment * (layer - 1) + localPos - 1)) ||
					!filled.contains(new Pair<Integer, Integer>(layer - 1, segment * (layer - 1) + localPos))) {
				success = false;
			}
		}
		
		++ringPos;
		if (ringPos > 6 * layer) {
			ringPos = 0;
			++layer;
			if (emptyLayer) {
				return null;
			}
			emptyLayer = true;
		}
		
		if (success) {
			emptyLayer = false;
			return next;
		}
		else {
			return getNextPosition();
		}
	}
	
	public void fillPosition() {
		filled.add(new Pair<Integer, Integer>(lastLayer, lastRingPos));
	}
	
	private int layer;
	private int ringPos;
	private int lastLayer;
	private int lastRingPos;
	private final float spacing;
	private Set<Pair<Integer, Integer>> filled;
	private static final int[] COPRIMES = new int[] {1, 1, 5, 7, 11, 13, 19};
	private boolean emptyLayer;
}
