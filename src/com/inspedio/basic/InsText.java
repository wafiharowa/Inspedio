package com.inspedio.basic;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import com.inspedio.core.InsCanvas;

/**
 * InsText represent simple Text for Drawing
 */
public class InsText extends InsBasic{

	public String text;
	public Font font;
	public int x;
	public int y;
	public int anchor;
	public int color;
	
	public InsText()
	{
		super();
		this.setValue("", 0, 0, Graphics.LEFT | Graphics.TOP, true);
		this.font = InsCanvas.defaultFont;
		this.color = InsCanvas.COLOR_WHITE;
	}
	
	public InsText(String Text, int X, int Y, int Anchor)
	{
		super();
		this.setValue(Text, X, Y, Anchor, true);
		this.font = InsCanvas.defaultFont;
		this.color = InsCanvas.COLOR_WHITE;
	}
	
	public void setValue(String Text, int X, int Y, int Anchor, boolean Visible)
	{
		this.text = Text;
		this.x = X;
		this.y = Y;
		this.anchor = Anchor;
		this.visible = Visible;
	}
	
	public void setPosition(int X, int Y)
	{
		this.x = X;
		this.y = Y;
	}
	
	public void draw(Graphics g)
	{
		if(this.visible)
		{
			g.setFont(this.font);
			g.setColor(this.color);
			g.drawString(this.text, this.x, this.y, this.anchor);
		}
	}
}
