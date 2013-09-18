package com.inspedio.entity.basic.shape;

import javax.microedition.lcdui.Graphics;


/**
 * <code>InsRect</code> represent basic rectangle shape
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsRect extends InsShape{
	
	/**
	 * Instantiate a new default Rectangle
	 */
	public InsRect()
	{
		this(0, 0, 10, 10);
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
	}
	
	public void draw(Graphics g)
	{
		if(this.fill)
		{
			g.setColor(this.borderColor);
			g.fillRect(this.position.x, this.position.y, this.size.width, this.size.height);
			g.setColor(this.fillColor);
			g.fillRect(this.position.x + this.borderWidth, this.position.y + this.borderWidth, this.size.width - (this.borderWidth * 2), this.size.height - (this.borderWidth * 2));
		}
		else
		{
			g.setColor(this.borderColor);
			for(int i = 0; i < this.borderWidth; i ++){
				g.drawRect(this.position.x, this.position.y, this.size.width, this.size.height);
			}
		}
	}
		
}
