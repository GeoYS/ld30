package com.aqua.ludum.ld30.g2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class takes a texture and splits it up into TextureRegions based on 
 * the regionWidth and regionHeight. It separates the regions by going left to right,
 * and top to bottom.
 * @author GeoYS_2
 *
 */
public class SpriteSheet {
	
	private Texture texture;
	private TextureRegion[] regions;
	
	public SpriteSheet(Texture texture, int regionWidth, int regionHeight){
		int rows = (int)(texture.getHeight() / regionHeight), cols = (int)(texture.getWidth() / regionWidth);
		regions = new TextureRegion[rows*cols];
		for(int i  = 0; i < rows; i ++){
			for(int j = 0; j < cols; j ++){
				regions[i*rows + j] = new TextureRegion(texture,
						j * regionWidth, i * regionHeight,
						regionWidth, regionHeight);
			}
		}
	}
	
	public TextureRegion[] getFrames(){
		return regions;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
