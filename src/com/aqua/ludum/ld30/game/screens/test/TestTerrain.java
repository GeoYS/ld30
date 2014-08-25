package com.aqua.ludum.ld30.game.screens.test;

import com.aqua.ludum.ld30.Constants;
import com.aqua.ludum.ld30.game.Block;
import com.aqua.ludum.ld30.game.ComputerPlayer;
import com.aqua.ludum.ld30.game.HumanPlayer;
import com.aqua.ludum.ld30.game.Player;
import com.aqua.ludum.ld30.game.Terrain;
import com.aqua.ludum.ld30.game.Worker;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class TestTerrain extends Terrain {

	public TestTerrain(OrthographicCamera camera) {
		super("../LD30/res/LudumDareMap01.tmx", camera);
		Player human = new HumanPlayer("George", this, camera);
		Player computer = new ComputerPlayer("AI", this);
		this.getPlayers().add(human);
		this.getPlayers().add(computer);
		
		addUnit(new Worker(computer, new Vector2(0, 0), this));
		addUnit(new Worker(computer, new Vector2(32, 0), this));
		addUnit(new Worker(computer, new Vector2(0, 32), this));
		/*this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(0, 0), this));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(64, 0), this));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(128, 0), this));
		this.addUnit(new Worker(this.getNeutralPlayer(), new Vector2(64, 64), this));*/
		
		/*List<Vector2> neighbors = getNeighbours(new Vector2(0, 0), new Vector2(0, 0));
		for (Vector2 n : neighbors) {
			this.addUnit(new Worker(this.getNeutralPlayer(), n, this));
		}*/
	}

	@Override
	public void render(SpriteBatch batch) {
		super.render(batch);
		renderer.setColor(Color.WHITE);
		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.begin(ShapeType.Line);
		for(Block block : this.getBlocks()) {
			renderBlock(block);
		}
		renderer.end();
	}
	
	private void renderBlock(Block block) {
		Vector2 v1 = block.getPosition(), 
				v2 = v1.cpy().add(block.getWidth(), 0),
				v3 = v1.cpy().add(block.getWidth(), block.getHeight()),
				v4 = v1.cpy().add(0, block.getHeight());
		v1 = Constants.worldToScreen(v1, this.getTilesHigh());
		v2 = Constants.worldToScreen(v2, this.getTilesHigh());
		v3 = Constants.worldToScreen(v3, this.getTilesHigh());
		v4 = Constants.worldToScreen(v4, this.getTilesHigh());
		
		float[] vertices = {v1.x, v1.y, v2.x, v2.y, v3.x, v3.y, v4.x, v4.y};
		renderer.polygon(vertices);
 	}
	
	private ShapeRenderer renderer = new ShapeRenderer();
	
}
