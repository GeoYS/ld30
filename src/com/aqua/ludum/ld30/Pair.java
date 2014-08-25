package com.aqua.ludum.ld30;

public class Pair<T, U> {
	
	public Pair(T t, U u) {
		this.t = t;
		this.u = u;
	}
	
	public T first() {
		return this.t;
	}
	
	public U second() {
		return this.u;
	}
	
	private final T t;
	private final U u;
	
}
