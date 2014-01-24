package com.inspedio.entity;

import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.primitive.InsImage;
import com.inspedio.enums.TransformType;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.helper.InsCamera;
import com.inspedio.system.helper.InsUtil;

/**
 * InsSprite is a sprite-based game object<br>
 * 
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsSprite extends InsBasic{

	// ATTRIBUTE
	/**
	 * The sprite of this unit.
	 * Use <code>InsAssets</code> for caching sprite and use single sprite for multiple Object (thus using less memory)
	 */
	protected InsImage image;
	
	public TransformType transform;
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
	 * Instantiates a <code>InsObject</code>.
	 * 
	 * @param	spritePath	Location of sprite image in res folder
	 * @param	X			The X-coordinate of the point in world space.
	 * @param	Y			The Y-coordinate of the point in world space.
	 * @param	Width		Desired width of object.
	 * @param	Height		Desired height of object.
	 */
	public InsSprite(String spritePath, int X, int Y, int Width, int Height)
	{
		super(X, Y, Width, Height);
		this.init();
		this.setImage(spritePath, Width, Height);
	}
	
	public InsSprite(String spritePath, int X, int Y)
	{
		super(X, Y, 0, 0);
		this.init();
		this.setImage(spritePath);
	}
	
	public InsSprite(String spritePath)
	{
		this(spritePath, InsGlobal.middleX, InsGlobal.middleY);
	}
	
	private void init()
	{
		this.transform = TransformType.NONE;
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
		this.image = null;
		this.transform = null;
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
	public void draw(Graphics g)
	{
		super.draw(g);
		if(this.image != null)
		{
			if(absolute)
			{
				this.image.drawFrame(g, this.frame, this.position.x, this.position.y, this.align, this.transform);
			}
			else
			{
				
				if(this.isOnScreen())
				{
					this.image.drawFrame(g, this.frame, this.position.x - this.camera.getLeft(), this.position.y - this.camera.getTop(), this.align, this.transform);
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
		return InsUtil.isCollideBox(this, this.camera);
	}
	
	/**
	 * Set Image and automatically adjust Width and Height
	 */
	public void setImage(String imagePath, int frameWidth, int frameHeight)
	{
		this.image = InsGlobal.cache.getImage(imagePath, frameWidth, frameHeight);
		this.setSize(this.image.frameWidth, this.image.frameHeight);
	}
	
	/**
	 * Set Image and automatically adjust Width and Height
	 */
	public void setImage(String imagePath)
	{
		this.image = InsGlobal.cache.getImage(imagePath);
		this.setSize(this.image.frameWidth, this.image.frameHeight);
	}
	
	/**
	 * Set Transformation State
	 */
	public void setTransform(TransformType Transform){
		this.transform = Transform;
	}
	
	/**
	 * Set Frame
	 */
	public void setFrame(int Frame){
		this.frame = Frame;
	}
	
	/**
	 * Whether X and Y pointer is inside object
	 */
	public boolean isOverlap(int X, int Y){
		if(absolute){
			return ((InsUtil.Absolute(X - this.getMiddleX()) <= (this.size.width / 2)) && (InsUtil.Absolute(Y - this.getMiddleY()) <= (this.size.height / 2)));
		} else {
			return ((InsUtil.Absolute((X - this.camera.position.x) - this.getMiddleX()) <= (this.size.width / 2)) && (InsUtil.Absolute((Y - this.camera.position.y) - this.getMiddleY()) <= (this.size.height / 2)));
		}
	}
	
}
