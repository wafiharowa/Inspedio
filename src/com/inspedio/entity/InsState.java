package com.inspedio.entity;

import java.util.Vector;

import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.helper.InsKeys;
import com.inspedio.system.helper.InsPointer;

/**
 * InsState represents GameState, like MenuState, or PlayState.<br>
 * Its basically a container of Game Object with ability to pause, reset, and load progressively.<br>
 * Some State can be very heavy (contain many objects), therefore it needs to load progressively.<br>
 * Because of that, State Constructor should be emptied, and object initiation should be declared on <code>create()</code><br>
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsState extends InsGroup{

	
	
	/**
	 * DO NOT Override this unless you know what you are doing.
	 */
	public InsState()
	{
		super();
	}
		
	/**
	 * Handling Key and PointerEvent before passing it to its member
	 */
	public void update()
	{
		this.handleKeyEvent(InsGlobal.keys);
		this.handlePointerEvent(InsGlobal.pointer);
		super.update();
	}
	
	/**
	 * This function is called after the game engine successfully switches states.
	 * Override this function, NOT the constructor, to initialize or set up your game state.
	 * We do NOT recommend overriding the constructor, unless you want some crazy unpredictable things to happen!
	 */
	public abstract void create();
	
	/**
	 * Things to do after create, and after set to Current GameState
	 */
	public void finishCreate()
	{
		InsGlobal.loadProgress = 100;
	}
	
	public void destroy()
	{
		super.destroy();
	}
	
	/**
	 * This function is called to reset game
	 * Default are destroying state and creating it
	 */
	public void reset()
	{
		this.clear();
		this.members = new Vector();
		this.create();
	}
	
	/**
	 * Pause State. Override this to implement your Pause Behavior
	 */
	public void pause()
	{
		
	}
	
	/**
	 * Resume State. Override this to implement your Resume Behavior
	 */
	public void resume()
	{
		
	}
	
	/**
	 * Override this to implement State KeyEvent handler, such as pausing game.
	 */
	public void handleKeyEvent(InsKeys key)
	{
		
	}
	
	/**
	 * Override this to implement State PointerEvent handler, such as pausing game.
	 */
	public void handlePointerEvent(InsPointer pointer)
	{
		for(int i = 0; i < pointer.pressed.length; i++){
			this.onPointerPressed(pointer.pressed[i].x, pointer.pressed[i].y);
		}
		
		for(int i = 0; i < pointer.released.length; i++){
			this.onPointerReleased(pointer.released[i].x, pointer.released[i].y);
		}
		
		for(int i = 0; i < pointer.dragged.length; i++){
			this.onPointerDragged(pointer.dragged[i].x, pointer.dragged[i].y);
		}
		
		for(int i = 0; i < pointer.hold.length; i++){
			this.onPointerHold(pointer.hold[i].x, pointer.hold[i].y);
		}
	}
	
	/**
	 * Action executed when LeftSoftKey Pressed
	 */
	public void onLeftSoftKey()
	{
		
	}
	
	/**
	 * Action executed when RightSoftKey Pressed
	 */
	public void onRightSoftKey()
	{
		
	}
	
	
}
