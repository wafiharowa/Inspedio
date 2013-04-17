package com.inspedio.basic.shape;

import com.inspedio.basic.InsBasic;
import com.inspedio.core.InsGlobal;

/**
 * <code>InsRect</code> represent basic rectangle shape
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsRect extends InsBasic{

	/**
	 * The color to be drawn
	 */
	public int color;
	/**
	 * TRUE if you want to draw filled rectangle. FALSE if only border
	 */
	public boolean fill;
	
	/**
	 * Instantiate a new default Rectangle
	 */
	public InsRect()
	{
		this(0, 0, 10, 10);
		this.color = 0;
		this.fill = true;
	}
	
	/**
	 * Instantiate a new rectangle.
	 * 
	 * @param	X		The X-coordinate of the point in space.
	 * @param	Y		The Y-coordinate of the point in space.
	 * @param	Width	Desired width of the rectangle.
	 * @param	Height	Desired height of the rectangle.
	 */
	public InsRect(int X, int Y, int Width, int Height)
	{
		super(X, Y, Width, Height);
		this.color = 0;
		this.fill = true;
	}
	
	public void draw()
	{
		InsGlobal.graphic.setColor(this.color);
		if(this.fill)
		{
			InsGlobal.graphic.fillRect(this.x, this.y, this.width, this.height);
		}
		else
		{
			InsGlobal.graphic.drawRect(this.x, this.y, this.width, this.height);
		}
	}
	
	public void setAttribute(int X, int Y, int Width, int Height, int Color, boolean Fill)
	{
		this.x = X;
		this.y = Y;
		this.width = Width;
		this.height = Height;
		this.color = Color;
		this.fill = Fill;
	}
	
}
