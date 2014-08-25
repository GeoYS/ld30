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
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Pair)) {
			return false;
		}
		Pair<?, ?> pOther = (Pair<?, ?>) other;
		return (pOther.t.equals(t) && pOther.u.equals(u));
	}
	
	@Override
	public int hashCode() {
		return t.hashCode() + u.hashCode();
	}
	
	@Override
	public String toString() {
		return "(" + t.toString() + ", " + u.toString() + ")";
	}
	
	private final T t;
	private final U u;
	
}
