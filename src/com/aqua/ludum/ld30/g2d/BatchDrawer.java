package com.aqua.ludum.ld30.g2d;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 * Allows simple transforms to be applied before drawing.
 * 
 * @author Duane Byer, George Shen
 *
 */
public abstract class BatchDrawer {

	public final void draw(SpriteBatch batch) {
		m_oldTransform.set(batch.getTransformMatrix());
		m_transform.set(m_oldTransform);
		applyTransform(m_transform);
		batch.setTransformMatrix(m_transform);
		drawImage(batch);
		batch.setTransformMatrix(m_oldTransform);
	}
	
	/**
	 * This method should draw the Entity at position (0, 0). The
	 * transformation matrices passed to the method will already
	 * have been prepared correctly.
	 * 
	 * @param batch
	 */
	public abstract void drawImage(SpriteBatch batch);
	
	public abstract void applyTransform(Matrix4 transform);
	
	private Matrix4 m_oldTransform = new Matrix4();
	private Matrix4 m_transform = new Matrix4();
}
