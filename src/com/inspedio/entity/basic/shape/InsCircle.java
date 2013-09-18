package com.inspedio.entity.basic.shape;

import javax.microedition.lcdui.Graphics;


public class InsCircle extends InsShape {

	public int radius;
	public int startAngle;
	public int arcAngle;
	
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
		this.fillColor = 0;
		this.startAngle = 0;
		this.arcAngle = 360;
		this.fill = Fill;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(this.fillColor);
		if(this.fill)
		{
			g.fillArc(this.position.x - this.radius, this.position.y - this.radius, this.radius, this.radius, this.startAngle, this.arcAngle);
		}
		else
		{
			g.drawArc(this.position.x - this.radius, this.position.y - this.radius, this.radius, this.radius, this.startAngle, this.arcAngle);
		}
	}
	
	public void setAttribute(int X, int Y, int Radius, int StartAngle, int ArcAngle, int Color, boolean Fill)
	{
		this.setPosition(X, Y);
		this.radius = Radius;
		this.startAngle = StartAngle;
		this.arcAngle = ArcAngle;
		this.fillColor = Color;
		this.fill = Fill;
	}

}
