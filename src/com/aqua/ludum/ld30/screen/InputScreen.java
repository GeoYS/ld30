package com.aqua.ludum.ld30.screen;

import java.util.ArrayList;

import com.badlogic.gdx.InputProcessor;

/**
 * A UpdateableScreen that has input processing capabilities.
 * This basically makes it convenient to add other input listeners 
 * to the Screen.
 * @author GeoYS_2
 *
 */
public abstract class InputScreen extends UpdateableScreen{

	private ArrayList<InputProcessor> processors = new ArrayList<InputProcessor>();
	
	public boolean addProcessor(InputProcessor ip){
		return processors.add(ip);
	}
	
	public boolean removeProcessor(InputProcessor ip){
		return processors.remove(ip);
	}
	
	public boolean removeAll(ArrayList<InputProcessor> inputProcessors){
		return processors.removeAll(inputProcessors);
	}
	
	public void clearProcessors() {
		processors.clear();
	}
	
	public boolean addAll(ArrayList<InputProcessor> inputProcessors){
		return processors.addAll(inputProcessors);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		for(InputProcessor ip : processors){
			if(ip.keyDown(keycode)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		for(InputProcessor ip : processors){
			if(ip.keyTyped(character)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		for(InputProcessor ip : processors){
			if(ip.keyUp(keycode)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		for(InputProcessor ip : processors){
			if(ip.mouseMoved(screenX, screenY)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		for(InputProcessor ip : processors){
			if(ip.scrolled(amount)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor ip : processors){
			if(ip.touchDown(screenX, screenY, pointer, button)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		for(InputProcessor ip : processors){
			if(ip.touchDragged(screenX, screenY, pointer)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		for(InputProcessor ip : processors){
			if(ip.touchUp(screenX, screenY, pointer, button)){
				return true;
			}
		}
		return false;
	}

}
