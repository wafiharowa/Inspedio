package com.inspedio.entity;

import javax.microedition.lcdui.Graphics;

/**
 * InsAtom is the atom of Game Engine.<br>
 * Every object used in game <b>MUST</b> inherit this.<br>
 * InsAtom have two main method which is update() and draw() for implementing game logic and rendering method respectively.<br>
 * It also have preUpdate() and postUpdate() for other spesific purpose.<br>
 * Place your object behavior in update() method (override it freely, don't forget to call super.update()).<br>
 * You can also modify rendering method in draw() method, for example for RepeatingBackground, Spesific BitMapFont, or other else that needs different render method.
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsAtom {

	// ATTRIBUTES
	/**
	 * This is Object Unique Identifier.<br>
	 * Object created inside Inspedio Engine will automatically get their unique identifier.<br>
	 * This is useful for tracking and debugging purpose.
	 */
	public String identifier;
	/**
	 * Decide whether <code>update()</code> and <code>draw()</code> are automatically called.<br>
	 * An existing Object will be updated and rendered.
	 */
	public boolean exists;
	/**
	 * Decide whether <code>update()</code> are automatically called.<br>
	 * An active Object will be updated.<br>
	 * Note that you can make an object active without actually rendering them, like invisible weapon.
	 */
	public boolean active;
	/**
	 * Decide whether <code>draw()</code> are automatically called.<br>
	 * A visible Object will be rendered.<br>
	 * Note that you can make an object visible without make them active, like a static Background.
	 */
	public boolean visible;
	/**
	 * Decide whether this object checked for collision.<br>
	 * Alive object will be considered for collision detector.<br>
	 * Some unit can be both active and visible, but not alive, for example a dead unit currently playingdeath animation.
	 */
	public boolean alive;
	/**
	 * This is a safeguard to prevent accessing an object which already deleted (or in the middle of deleting process).<br>
	 * When an object deleted, it will set this attribute as deleted first before actually deleting it.
	 * 
	 */
	public boolean deleted;
	
	
	// CONSTRUCTORS
	/**
	 * Constructor. Instantiate basic object.
	 */
	public InsAtom()
	{
		this.exists = true;
		this.active = true;
		this.visible = true;
		this.alive = true;
		this.deleted = false;
	}
	
	/**
	 * Destructor. Destroy object and free memory (Java Garbage Collector will dispose this).<br>
	 * Note that if you have other InsAtom inside it, you should manually destroy it.<br>
	 * You should set its reference to null after destroying it, so Java GC will clear both of them.
	 */
	public void destroy()
	{
		this.deleted = true;
		this.exists = false;
	}
	
	/**
	 * Pre-update is called right before <code>update()</code> on each object in the game loop.<br>
	 * Usually consist of Object State Checker, Iterator Decrement/Increment, etc.
	 */
	public abstract void preUpdate();
	
	/** 
	 * Update is the core logic of game object.<br>
	 * Processing input, play Animation, executing action, etc.<br>
	 */
	public  abstract void update();
	
	/**
	 * Post-update is called right after <code>update()</code> on each object in the game loop.<br>
	 * Usually consist of Collision Detection, Object Position Verification, Camera handling, etc.
	 */
	public abstract void postUpdate();
	
	
	/**
	 * Draw implement how Object should be rendered/drawn.<br>
	 * Usually it doesn't need to be modified, unless you want some special draw implementation such as draw 1 pixel background repeatedly.
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Method for handling pointerPressed
	 */
	public abstract boolean onPointerPressed(int X, int Y);
	
	/**
	 * Method for handling pointerReleased
	 */
	public abstract boolean onPointerReleased(int X, int Y);
	
	/**
	 * Method for handling pointerDragged
	 */
	public abstract boolean onPointerDragged(int X, int Y);
	
	/**
	 * Method for handling pointerHold
	 */
	public abstract boolean onPointerHold(int X, int Y);
	
}
