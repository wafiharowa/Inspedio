package com.inspedio.basic;

import javax.microedition.lcdui.Graphics;

import com.inspedio.core.InsGlobal;
import com.inspedio.helper.InsKeys;

/**
 * InsBasic represent any game object which have position and size.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsBasic extends InsAtom{

	// ATTRIBUTE
	/**
	 * X position of the upper left corner of this object in world space
	 */
	public int x;
	/**
	 * Y position of the upper left corner of this object in world space
	 */
	public int y;
	/**
	 * The width of this object
	 */
	public int width;
	/**
	 * The height of this object
	 */
	public int height;
	
	protected InsAction action;
	
	/**
	 * Instantiates a default <code>InsBasic</code> object.
	 */
	public InsBasic()
	{
		this(0, 0, 0, 0);
	}
	
	/**
	 * Instantiates a <code>InsBasic</code> with given size and position.
	 * 
	 * @param	X		The X-coordinate of the point in world space.
	 * @param	Y		The Y-coordinate of the point in world space.
	 * @param	Width	Desired width of object.
	 * @param	Height	Desired height of object.
	 */
	public InsBasic(int X, int Y, int Width, int Height)
	{
		super();
		this.x = X;
		this.y = Y;
		this.width = Width;
		this.height = Height;
		this.action = null;
	}
	
	/**
	 * Set Object Size
	 */
	public void setSize(int Width, int Height)
	{
		this.width = Width;
		this.height = Height;
	}
	
	/**
	 * Set Object Position
	 */
	public void setPosition(int X, int Y)
	{
		this.x = X;
		this.y = Y;
	}
	
	/**
	 * Set this object Middle Point to given point<br>
	 * Useful for centering object into square
	 */
	public void setMiddlePoint(int X, int Y)
	{
		this.x = X - (this.width / 2);
		this.y = Y - (this.height / 2);
	}

	public void preUpdate() {
		
	}

	public void update() {
		handleKeyState(InsGlobal.keys);
		if(this.action != null){
			this.action.act();
		}
	}

	public void postUpdate() {
		
	}

	public void draw(Graphics g) {
		
	}
	
	public void setAction(InsAction Action){
		this.action = Action;
	}
	
	public void unsetAction(){
		this.action = null;
	}
	
	/**
	 * Override this to implement keyHandler
	 */
	protected void handleKeyState(InsKeys key)
	{
	}
	
	/**
	 * Override this to implement touchPressed behavior
	 * 
	 * @return	TRUE if you want touchEvent to not passed to next Object
	 */
	protected boolean onTouchPressed(){
		return false;
	}
	
	/**
	 * Override this to implement touchReleased behavior
	 * 
	 * @return	TRUE if you want touchEvent to not passed to next Object
	 */
	protected boolean onTouchReleased(){
		return false;
	}

	/**
	 * Override this to implement touchDragged behavior
	 * 
	 * @return	TRUE if you want touchEvent to not passed to next Object
	 */
	protected boolean onTouchDragged(){
		return false;
	}
	
	/**
	 * Do not override this unless you want to specifically access coordinate touched
	 */
	public boolean onPointerPressed(int X, int Y) {
		X -= this.x;
		Y -= this.y;
		if((X >= 0 && X <= this.width) && (Y >= 0 && Y <= this.height)){
			return onTouchPressed();
		}
		return false;
	}

	public boolean onPointerReleased(int X, int Y) {
		X -= this.x;
		Y -= this.y;
		if((X >= 0 && X <= this.width) && (Y >= 0 && Y <= this.height)){
			return onTouchReleased();
		}
		return false;
	}

	public boolean onPointerDragged(int X, int Y) {
		X -= this.x;
		Y -= this.y;
		if((X >= 0 && X <= this.width) && (Y >= 0 && Y <= this.height)){
			return onTouchDragged();
		}
		return false;
	}
}
