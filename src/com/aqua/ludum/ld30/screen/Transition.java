package com.aqua.ludum.ld30.screen;

import com.badlogic.gdx.Screen;

/**
 * Blank transition.
 * @author GeoYS_2
 *
 */
public class Transition {
	
	/**
	 * Begin transition out of this screen.
	 * @param otherScreen Might be useful for fancy transitions.
	 */
	public void startOutTransition(Screen otherScreen){
	}
	
	/**
	 * Has this screen finished transition-ing out?
	 * @return
	 */
	public boolean outTransitionFinished(){
		return true;
	}
	
	/**
	 * Draw what should be drawn when transition-ing out.
	 */
	public void renderOutTransition(){
	}
	
	/**
	 * Update the transition out.
	 */
	public void updateOutTransition(float delta){
	}
	
	/**
	 * Begin transition in of this screen.
	 * @param otherScreen Might be useful for fancy transitions.
	 */
	public void startInTransition(Screen otherScreen){
	}
	
	/**
	 * Has this screen finished transition-ing in?
	 * @return
	 */
	public boolean inTransitionFinished(){
		return true;
	}
	
	/**
	 * Draw what should be drawn when transition-ing in.
	 */
	public void renderInTransition(){
	}
	
	/**
	 * Update the transition in.
	 */
	public void updateInTransition(float delta){
	}	
	
}
