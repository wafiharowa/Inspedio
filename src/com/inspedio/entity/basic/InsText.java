package com.inspedio.entity.basic;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.InsBasic;
import com.inspedio.system.core.InsCanvas;

/**
 * InsText represent simple Text for Drawing
 */
public class InsText extends InsBasic{

	public String text;
	public Font font;
	public int color;
	
	public InsText()
	{
		this("", 0, 0);
	}
	
	public InsText(String Text, int X, int Y)
	{
		super();
		this.font = InsCanvas.defaultFont;
		this.color = InsCanvas.COLOR_WHITE;
		this.visible = true;
		this.setPosition(X, Y);
		this.setText(Text);
	}
	
	public void setText(String Text)
	{
		this.text = Text;
	}
		
	public void draw(Graphics g)
	{
		if(this.visible)
		{
			g.setFont(this.font);
			g.setColor(this.color);
			g.drawString(this.text, this.position.x, this.position.y, this.align.horizontal.getValue() | Graphics.BASELINE);
		}
	}
}
