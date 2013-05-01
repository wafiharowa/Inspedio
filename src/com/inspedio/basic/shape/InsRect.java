package com.inspedio.basic.shape;

import javax.microedition.lcdui.Graphics;

import com.inspedio.basic.InsBasic;

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
			g.fillRect(this.x, this.y, this.width, this.height);
			g.setColor(this.fillColor);
			g.fillRect(this.x + this.borderWidth, this.y + this.borderWidth, this.width - (this.borderWidth * 2), this.height - (this.borderWidth * 2));
		}
		else
		{
			g.setColor(this.borderColor);
			for(int i = 0; i < this.borderWidth; i ++){
				g.drawRect(this.x, this.y, this.width, this.height);
			}
		}
	}
	
	public boolean isInside(int X, int Y) {
		return ((X >= this.x) && (X <= this.x + this.width) && (Y >= this.y) && (Y <= this.y + this.height));
	}

	public boolean isCollide(InsBasic b) {
		return (b.x < this.x + this.width) && (this.x < b.x + b.width) && (b.y < this.y + this.height) && (this.y < b.y + b.height);
	}
	
}
