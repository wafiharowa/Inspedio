package com.inspedio.basic.shape;

import com.inspedio.core.InsGlobal;


public class InsRoundedRect extends InsRect{

	public int archHeight;
	public int archWidth;
	
	public InsRoundedRect()
	{
		super();
		this.init();
	}
	
	public InsRoundedRect(int X, int Y, int Width, int Height)
	{
		super(X, Y, Width, Height);
		this.init();
	}
	
	public void init()
	{
		this.archHeight = 5;
		this.archWidth = 5;
	}
	
	public void draw()
	{
		InsGlobal.graphic.setColor(this.color);
		if(this.fill)
		{
			InsGlobal.graphic.fillRoundRect(this.x, this.y, this.width, this.height, this.archWidth, this.archHeight);
		}
		else
		{
			InsGlobal.graphic.drawRoundRect(this.x, this.y, this.width, this.height, this.archWidth, this.archHeight);
		}
	}
}
