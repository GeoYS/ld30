package com.aqua.ludum.ld30.screen;

import com.badlogic.gdx.Screen;

/**
 * A Screen that handles transition-ing in and out of this screen.
 * Fully optional, you can equally use an InputScreen instead,
 * it will just result in an instant transition.
 * You can set simple overlay transitions (eg. fade to black).
 * @author GeoYS_2
 *
 */
public abstract class GameScreen extends InputScreen{
	
	/*
	 * Simple overlay transitions (eg. fade to black).
	 */
	private Transition transition = null;
	
	/**
	 * You can set simple overlay transitions (eg. fade to black).
	 * Do not override any transition methods if this is used.
	 */
	public void setTransition(Transition transition){
		this.transition = transition;
	}
	
	/**
	 * Begin transition out of this screen.
	 * @param otherScreen Might be useful for fancy transitions.
	 */
	public void startOutTransition(Screen otherScreen){
		if(transition != null){
			transition.startOutTransition(otherScreen);
		}
	}
	
	/**
	 * Has this screen finished transition-ing out?
	 * @return
	 */
	public boolean outTransitionFinished(){
		return transition != null ? transition.outTransitionFinished() : true;
	}
	
	/**
	 * Draw what should be drawn when transition-ing out.
	 */
	public void renderOutTransition(){
		if(transition != null){
			transition.renderOutTransition();
		}
	}
	
	/**
	 * Update the transition out.
	 */
	public void updateOutTransition(float delta){
		if(transition != null){
			transition.updateOutTransition(delta);
		}
	}
	
	public final void renderOut(float delta){
		this.renderOutTransition();
		this.updateOutTransition(delta);
	}
	
	/**
	 * Begin transition in of this screen.
	 * @param otherScreen Might be useful for fancy transitions.
	 */
	public void startInTransition(Screen otherScreen){
		if(transition != null){
			transition.startInTransition(otherScreen);
		}
	}
	
	/**
	 * Has this screen finished transition-ing in?
	 * @return
	 */
	public boolean inTransitionFinished(){
		return transition != null ? transition.inTransitionFinished() : true;
	}
	
	/**
	 * Draw what should be drawn when transition-ing in.
	 */
	public void renderInTransition(){
		if(transition != null){
			transition.renderInTransition();
		}
	}
	
	/**
	 * Update the transition in.
	 */
	public void updateInTransition(float delta){
		if(transition != null){
			transition.updateInTransition(delta);
		}
	}	
	
	public final void renderIn(float delta){
		this.renderInTransition();
		this.updateInTransition(delta);
	}
}
