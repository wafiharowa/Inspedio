package com.inspedio.basic.shape;

import com.inspedio.basic.InsBasic;
import com.inspedio.core.InsGlobal;

public class InsCircle extends InsBasic {

	public int radius;
	public int startAngle;
	public int arcAngle;
	public int color;
	public boolean fill;
	
	/**
	 * Instantiate a new default Rectangle
	 */
	public  InsCircle()
	{
		this(0, 0, 5, true);
	}
	
	/**
	 * Instantiate a new rectangle.
	 * 
	 * @param	X		The X-coordinate of the point in space.
	 * @param	Y		The Y-coordinate of the point in space.
	 * @param	Radius	Circle Radius
	 * @param	Fill	TRUE if you want to draw full circle, FALSE for drawing border only
	 */
	public InsCircle(int X, int Y, int Radius, boolean Fill)
	{
		super(X, Y, Radius, Radius);
		this.radius = Radius;
		this.color = 0;
		this.startAngle = 0;
		this.arcAngle = 360;
		this.fill = Fill;
	}
	
	public void draw()
	{
		InsGlobal.graphic.setColor(this.color);
		if(this.fill)
		{
			InsGlobal.graphic.fillArc(this.x - this.radius, this.y - this.radius, this.radius, this.radius, this.startAngle, this.arcAngle);
		}
		else
		{
			InsGlobal.graphic.drawArc(this.x - this.radius, this.y - this.radius, this.radius, this.radius, this.startAngle, this.arcAngle);
		}
	}
	
	public void setAttribute(int X, int Y, int Radius, int StartAngle, int ArcAngle, int Color, boolean Fill)
	{
		this.x = X;
		this.y = Y;
		this.radius = Radius;
		this.startAngle = StartAngle;
		this.arcAngle = ArcAngle;
		this.color = Color;
		this.fill = Fill;
	}
}
