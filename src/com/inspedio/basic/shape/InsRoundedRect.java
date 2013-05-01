package com.inspedio.basic.shape;

import javax.microedition.lcdui.Graphics;


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
	
	public void draw(Graphics g)
	{
		if(this.fill)
		{
			g.setColor(this.borderColor);
			g.fillRoundRect(this.x, this.y, this.width, this.height, this.archWidth, this.archHeight);
			g.setColor(this.fillColor);
			g.fillRoundRect(this.x + this.borderWidth, this.y + this.borderWidth, this.width - (this.borderWidth * 2), this.height - (this.borderWidth * 2), this.archWidth, this.archHeight);
		}
		else
		{
			g.setColor(this.borderColor);
			for(int i = 0; i < this.borderWidth; i ++){
				g.drawRoundRect(this.x, this.y, this.width, this.height, this.archWidth, this.archHeight);
			}
		}
	}
}
