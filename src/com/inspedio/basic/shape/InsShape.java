package com.inspedio.basic.shape;

import com.inspedio.basic.InsBasic;

/**
 * <code>InsShape</code> is a basic shape, which can be dynamically changed.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsShape extends InsBasic{

	/**
	 * The color to be drawn inside of shape
	 */
	public int fillColor;
	/**
	 * TRUE if you want to draw filled rectangle. FALSE if only border
	 */
	public boolean fill;
	/**
	 * The color of shape border
	 */
	public int borderColor;
	/**
	 * The width of Border
	 */
	public int borderWidth;
	
	public InsShape(int X, int Y, int Width, int Height){
		super(X, Y, Width, Height);
		this.fillColor = 0x000000;
		this.fill = true;
		this.borderColor = 0xFFFFFF;
		this.borderWidth = 1;
	}
	
	public void setMainAttribute(int X, int Y, int Width, int Height)
	{
		this.x = X;
		this.y = Y;
		this.width = Width;
		this.height = Height;
	}
	
	public void setDetailAttribute(int FillColor, boolean Fill, int BorderColor, int BorderWidth){
		this.fillColor = FillColor;
		this.fill = Fill;
		this.borderColor = BorderColor;
		this.borderWidth = BorderWidth;
	}
	
	public abstract boolean isInside(int X, int Y);
	
	public abstract boolean isCollide(InsBasic b);
	
}
