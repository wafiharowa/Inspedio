package com.inspedio.helper;

import com.inspedio.core.InsCanvas;

/**
 * This is the helper class for key input
 * Useful for global detection of keyEvent (Pressed, Released, etc)
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsKeys {
	public static final int KEY_COUNT = 9;
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	public static final int FIRE = 4;
	public static final int GAME_A = 5;
	public static final int GAME_B = 6;
	public static final int GAME_C = 7;
	public static final int GAME_D = 8;
	
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
		this.currentKeyState[UP] = ((keystate & InsCanvas.UP_PRESSED) != 0);
		this.currentKeyState[DOWN] = ((keystate & InsCanvas.DOWN_PRESSED) != 0);
		this.currentKeyState[LEFT] = ((keystate & InsCanvas.LEFT_PRESSED) != 0);
		this.currentKeyState[RIGHT] = ((keystate & InsCanvas.RIGHT_PRESSED) != 0);
		this.currentKeyState[FIRE] = ((keystate & InsCanvas.FIRE_PRESSED) != 0);
		this.currentKeyState[GAME_A] = ((keystate & InsCanvas.GAME_A_PRESSED) != 0);
		this.currentKeyState[GAME_B] = ((keystate & InsCanvas.GAME_B_PRESSED) != 0);
		this.currentKeyState[GAME_C] = ((keystate & InsCanvas.GAME_C_PRESSED) != 0);
		this.currentKeyState[GAME_D] = ((keystate & InsCanvas.GAME_D_PRESSED) != 0);		
	}
	
	
	/**
	 * Return TRUE if the given key currently pressed
	 */
	public boolean keyPressed(int keyCode)
	{
		return this.currentKeyState[keyCode];
	}
	
	/**
	 * Return TRUE if the given key was just pressed
	 */
	public boolean justPressed(int keyCode)
	{
		return (this.currentKeyState[keyCode] && !this.lastKeyState[keyCode]);
	}
	
	/**
	 * Return TRUE if the given key currently released (not pressed)
	 */
	public boolean keyReleased(int keyCode)
	{
		return !this.currentKeyState[keyCode];
	}
	
	/**
	 * Return TRUE if the given key was just released
	 */
	public boolean justReleased(int keyCode)
	{
		return (!this.currentKeyState[keyCode] && this.lastKeyState[keyCode]);
	}
}
