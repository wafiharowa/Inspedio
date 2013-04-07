package com.inspedio.basic;

import com.inspedio.helper.InsCamera;

/**
 * InsObject is a game object that have its own Sprite<br>
 * InsObject have individual frame to enable caching, so many object can share single sprite<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsObject extends InsBasic{

	// ATTRIBUTE
	/**
	 * The sprite of this unit.
	 * Use <code>InsAssets</code> for caching sprite and use single sprite for multiple Object (thus using less memory)
	 */
	public InsSprite sprite;
	/**
	 * Camera used for this object. Automatically set to global camera if null
	 */
	public InsCamera camera;
	/**
	 * Current frame sprite, change this on update to do animation (not sprite.frame)
	 * If you use <code>InsAssets</code> for caching Image
	 * All unit with same Class will point toward same Sprite (for memory efficiency)
	 * During draw, the sprite frame will be changed before draw
	 * So multiple unit with different frame can be drawn independently regardless of Sprite's actual frame. 
	 * 
	 * Frame can also be managed with <code>InsAnimm</code> for better handling
	 */
	public int frame;
	/**
	 * Controls whether the position of this objects affected by camera or not.
	 * Most Objects during play, such as Player, Monster are diplayed relative to their camera.
	 * Absolute objects are those who are not affected by camera, such as HUD.
	 */
	public boolean absolute;
	
	// CONSTRUCTOR
	/**
	 * Instantiates a default <code>InsObject</code>.
	 */
	public InsObject()
	{
		this(0, 0, 0, 0);
	}
	/**
	 * Instantiates a <code>InsObject</code>.
	 * 
	 * @param	X		The X-coordinate of the point in space.
	 * @param	Y		The Y-coordinate of the point in space.
	 */
	public InsObject(int X, int Y)
	{
		this(X, Y, 0, 0);
	}
	/**
	 * Instantiates a <code>InsObject</code>.
	 * 
	 * @param	X		The X-coordinate of the point in space.
	 * @param	Y		The Y-coordinate of the point in space.
	 * @param	Width	Desired width of the rectangle.
	 * @param	Height	Desired height of the rectangle.
	 */
	public InsObject(int X, int Y, int Width, int Height)
	{
		super(X, Y, Width, Height);
		this.init();
	}
	
	private void init()
	{
		//this.camera = InsGlobal.camera;
		this.absolute = false;
		this.frame = 0;	
	}
	
	
	public void preUpdate() {
	}

	public void update() {
	}

	public void postUpdate() {
	}

	public void draw() {
		
	}

}
