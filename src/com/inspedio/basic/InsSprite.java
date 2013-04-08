package com.inspedio.basic;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import com.inspedio.core.InsGlobal;
import com.inspedio.helper.primitive.InsPoint;

/**
 * <code>InsSprite</code> can be a single sprite, or behave like spritesheet<br>
 * use <code>InsCache</code> to optimize memory
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsSprite {

	protected Image imageSource;
	public String filePath;
	public int frameWidth;
	public int frameHeight;
	protected int frameCountX;
	protected int frameCountY;
	protected int frameTotal;
	
	public InsPoint position;
	public int frame;
	
	/**
	 * Create single frame InsSprite from image
	 * 
	 * @param	imagePath	File path of Image
	 */
	public InsSprite(String imagePath)
	{
		try
		{
			this.imageSource = Image.createImage(imagePath);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.frameWidth = this.imageSource.getWidth();
		this.frameHeight = this.imageSource.getHeight();
		this.filePath = imagePath;
		this.position = new InsPoint();
		this.frame = 0;
		this.frameCountX = 1;
		this.frameCountY = 1;
		this.frameTotal = 1;
	}
	
	/**
	 * Construct a new Sprite from a spritesheet, and splits it into many frames
	 * 
	 * @param	imagePath	File path of Spritesheet Image
	 * @param	frameWidth	The width of each frame
	 * @param	frameHeight	The height of each frame
	 */
	public InsSprite(String imagePath, int frameWidth, int frameHeight)
	{
		try
		{
			this.imageSource = Image.createImage(imagePath);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.filePath = imagePath;
		this.position = new InsPoint();
		this.frame = 0;
		this.frameCountX = this.imageSource.getWidth() / this.frameWidth;
		this.frameCountY = this.imageSource.getHeight() / this.frameWidth;
		this.frameTotal = this.frameCountX * this.frameCountY;
	}
	
	/**
	 * Create InsSprite from another InsSprite
	 */
	public InsSprite(InsSprite s)
	{
		this.imageSource = s.imageSource;
		this.frameWidth = s.frameWidth;
		this.frameHeight = s.frameHeight;
		this.filePath = s.filePath;
		this.position = new InsPoint(s.position.x, s.position.y);
		this.frame = s.frame;
		this.frameCountX = s.frameCountX;
		this.frameCountY = s.frameCountY;
		this.frameTotal = s.frameTotal;
	}
	
	public void destroy()
	{
		this.imageSource = null;
	}
	
	/**
	 * Draw Sprite to global canvas
	 */
	public void draw()
	{
		this.draw(InsGlobal.graphic);
	}
	
	/**
	 * Draw Sprite to specific canvas
	 */
	public void draw(Graphics g)
	{
		int x_src = (this.frame % this.frameCountX) * this.frameWidth;
		int y_src = ((int) (this.frame / this.frameCountX)) * this.frameHeight;	
		g.drawRegion(this.imageSource, x_src, y_src, this.frameWidth, this.frameHeight, 0, this.position.x, this.position.y, 0);
	}
	
	public void setFrame(int frame)
	{
		this.frame = frame;
	}
	
	public void setPosition(int X, int Y)
	{
		this.position.x = X;
		this.position.y = Y;
	}
	
	public void setMiddlePoint(int X, int Y)
	{
		this.position.x = X - (frameWidth / 2);
		this.position.y = Y - (frameHeight / 2);
	}
}
