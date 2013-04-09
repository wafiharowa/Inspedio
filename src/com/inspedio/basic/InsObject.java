package com.inspedio.basic;

import com.inspedio.core.InsGlobal;
import com.inspedio.helper.InsCamera;

/**
 * InsObject is a game object that have its own Sprite<br>
 * InsObject have individual frame to enable caching, so many object can share single sprite
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
	 * Current frame sprite, change this on update to do animation (not sprite.frame).<br>
	 * Frame can also be managed with <code>InsAnimm</code> for better handling
	 */
	public int frame;
	/**
	 * Controls whether the position of this objects affected by camera or not.<br>
	 * Most Objects during play, such as Player, Monster are diplayed relative to their camera.<br>
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
	 * @param	X		The X-coordinate of the point in world space.
	 * @param	Y		The Y-coordinate of the point in world space.
	 * @param	Width	Desired width of object.
	 * @param	Height	Desired height of object.
	 */
	public InsObject(int X, int Y, int Width, int Height)
	{
		super(X, Y, Width, Height);
		this.init();
	}
	/**
	 * Instantiates a <code>InsObject</code>.
	 * 
	 * @param	spritePath	Location of sprite image in res folder
	 * @param	X			The X-coordinate of the point in world space.
	 * @param	Y			The Y-coordinate of the point in world space.
	 * @param	Width		Desired width of object.
	 * @param	Height		Desired height of object.
	 * @param	useCache	TRUE if you want sprite generated from cache.
	 */
	public InsObject(String spritePath, int X, int Y, int Width, int Height, boolean useCache)
	{
		super(X, Y, Width, Height);
		this.init();
	}
	
	private void init()
	{
		this.camera = InsGlobal.camera;
		this.absolute = false;
		this.frame = 0;	
	}
	
	/**
	 * Override this function to null out variables or
	 * manually call destroy() on class members if necessary.
	 * Don't forget to call super.destroy()!
	 */
	public void destroy()
	{
		super.destroy();
		this.camera = null;
		this.sprite = null;
	}
	
	/**
	 * Updating Sprite Animation, or handles keystate here
	 */
	public void update()
	{
		super.update();
	}
	
	
	/**
	 * Set Sprite X and Y depend on Camera, setting its frame
	 * then Draw sprite to canvas
	 */
	public void draw()
	{
		super.draw();
		if(this.sprite != null)
		{
			if(absolute)
			{
				this.sprite.setPosition(this.x, this.y);
				this.sprite.setFrame(this.frame);
				this.sprite.draw();
			}
			else
			{
				
				if(this.isOnScreen())
				{
					this.sprite.setPosition((this.x - this.camera.x), (this.y - this.camera.y));
					this.sprite.setFrame(this.frame);
					this.sprite.draw();
				}
			}	
		}
	}
	
	/**
	 * Check whether the object is currently on screen camera
	 * 
	 * @return	TRUE if the object is on screen
	 */
	public boolean isOnScreen()
	{
		return (this.x <= this.camera.x + this.camera.width) && (this.camera.x <= this.x + this.width) && (this.y <= this.camera.y + this.camera.height) && (this.camera.y <= this.y + this.height);
	}
	
	
	/**
	 * Set Sprite and automatically adjust Width and Height
	 */
	public void setSprite(InsSprite s)
	{
		this.sprite = s;
		this.width = s.frameWidth;
		this.height = s.frameHeight;
	}

}
