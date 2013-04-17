package com.inspedio.basic.shape;

import com.inspedio.basic.InsBasic;
import com.inspedio.core.InsGlobal;
import com.inspedio.helper.primitive.InsPoint;

public class InsTriangle extends InsBasic{

	public InsPoint p1;
	public InsPoint p2;
	public InsPoint p3;
	/**
	 * Color to fill rectangle
	 */
	public int color;
	/**
	 * TRUE if fill, FALSE if only to draw
	 */
	public boolean fill;
	
	/**
	 * Instantiate a new default Rectangle
	 */
	public InsTriangle()
	{
		super();
		this.p1 = new InsPoint();
		this.p2 = new InsPoint();
		this.p3 = new InsPoint();
		this.color = 0;
		this.fill = true;
	}
	
	/**
	 * Instantiate a new rectangle.
	 * 
	 * @param	P1		First Point of triangle
	 * @param	P2		Second Point of triangle
	 * @param	P3		Third Point of triangle
	 * @param	Color	Triangle Color
	 * @param	Fill	TRUE for filled rectangle, FALSE for border only
	 * 
	 */
	public InsTriangle(InsPoint P1, InsPoint P2, InsPoint P3, int Color, boolean Fill)
	{
		super();
		this.setAttribute(P1, P2, P3, Color, Fill);
	}
	
	public void draw()
	{
		InsGlobal.graphic.setColor(this.color);
		if(this.fill)
		{
			InsGlobal.graphic.fillTriangle(this.p1.x, this.p1.y, this.p2.x, this.p2.y, this.p3.x, this.p3.y);
		}
		else
		{
			InsGlobal.graphic.drawLine(this.p1.x, this.p1.y, this.p2.x, this.p2.y);
			InsGlobal.graphic.drawLine(this.p1.x, this.p1.y, this.p3.x, this.p3.y);
			InsGlobal.graphic.drawLine(this.p2.x, this.p2.y, this.p3.x, this.p3.y);
		}
	}
	
	public void setAttribute(InsPoint P1, InsPoint P2, InsPoint P3, int Color, boolean Fill)
	{
		this.p1 = P1;
		this.p2 = P2;
		this.p3 = P3;
		this.color = Color;
		this.fill = Fill;
	}
}
