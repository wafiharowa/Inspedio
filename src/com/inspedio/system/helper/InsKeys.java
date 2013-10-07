package com.inspedio.system.helper;

import com.inspedio.enums.KeyCode;
import com.inspedio.system.core.InsCanvas;

/**
 * This is the helper class for key input
 * Useful for global detection of keyEvent (Pressed, Released, etc)
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsKeys {
	public static final int KEY_COUNT = 9;
		
	/**
	 * KeyState on Last Cycle
	 */
	public boolean[] lastKeyState;
	/**
	 * KeyState on Current Cycle
	 */
	public boolean[] currentKeyState;
	
	public InsKeys()
	{
		this.lastKeyState = new boolean[KEY_COUNT];
		this.currentKeyState = new boolean[KEY_COUNT];
		for(int i = 0; i < KEY_COUNT; i++)
		{
			this.lastKeyState[i] = false;
			this.currentKeyState[i] = false;
		}
	}
	
	/**
	 * Reset all KeyState
	 */
	public void resetKeyState()
	{
		for(int i = 0; i < KEY_COUNT; i++)
		{
			this.lastKeyState[i] = false;
			this.currentKeyState[i] = false;
		}
	}
	
	/**
	 * Update KeyState with KeyState from InsCanvas
	 */
	public void updateKeyState(int keystate)
	{
		// Set currentKey to lastKey
		for(int i = 0; i < KEY_COUNT; i++)
		{
			this.lastKeyState[i] = this.currentKeyState[i];
		}
		
		// Assign currentKey based on keyState
		this.setCurrentKeyState(KeyCode.UP, ((keystate & InsCanvas.UP_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.DOWN, ((keystate & InsCanvas.DOWN_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.LEFT, ((keystate & InsCanvas.LEFT_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.RIGHT, ((keystate & InsCanvas.RIGHT_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.FIRE, ((keystate & InsCanvas.FIRE_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.GAME_A, ((keystate & InsCanvas.GAME_A_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.GAME_B, ((keystate & InsCanvas.GAME_B_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.GAME_C, ((keystate & InsCanvas.GAME_C_PRESSED) != 0));
		this.setCurrentKeyState(KeyCode.GAME_D, ((keystate & InsCanvas.GAME_D_PRESSED) != 0));
	}
	
	protected void setCurrentKeyState(KeyCode code, boolean state){
		this.currentKeyState[code.getValue()] = state;
	}
	
	/**
	 * Return TRUE if the given key currently pressed
	 */
	public boolean keyPressed(KeyCode code)
	{
		return this.currentKeyState[code.getValue()];
	}
	
	/**
	 * Return TRUE if the given key was just pressed
	 */
	public boolean justPressed(KeyCode code)
	{
		return (this.currentKeyState[code.getValue()] && !this.lastKeyState[code.getValue()]);
	}
	
	/**
	 * Return TRUE if the given key currently released (not pressed)
	 */
	public boolean keyReleased(KeyCode code)
	{
		return !this.currentKeyState[code.getValue()];
	}
	
	/**
	 * Return TRUE if the given key was just released
	 */
	public boolean justReleased(KeyCode code)
	{
		return (!this.currentKeyState[code.getValue()] && this.lastKeyState[code.getValue()]);
	}
	
	public boolean isAnythingPressed(){
		boolean pressed = false;
		for(int i = 0; i < KEY_COUNT; i++)
		{
			if(this.currentKeyState[i]){
				pressed = true;
				break;
			}
		}
		
		return pressed;
	}
}
