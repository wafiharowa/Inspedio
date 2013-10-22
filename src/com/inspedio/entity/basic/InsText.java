package com.inspedio.entity.basic;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.InsBasic;
import com.inspedio.enums.FontSize;
import com.inspedio.enums.FontStyle;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.core.InsCanvas;

/**
 * InsText represent simple Text for Drawing
 */
public class InsText extends InsBasic{

	protected boolean singleLine;
	protected String[] texts;
	protected String text;
	protected Font font;
	protected int color;
	protected int fontHeight;
	
	public InsText()
	{
		this("", 0, 0);
	}
	
	public InsText(String Text, int X, int Y)
	{
		super();
		this.font = InsCanvas.defaultFont;
		this.color = InsCanvas.COLOR_BLACK;
		this.visible = true;
		this.fontHeight = this.font.getHeight();
		this.setPosition(X, Y);
		this.setText(Text);
		this.singleLine = true;
		this.texts = null;
		
	}
	
	/**
	 * Set Single Line Text
	 */
	public void setText(String Text)
	{
		this.text = Text;
		this.singleLine = true;
		this.size.height = this.fontHeight;
	}
	
	/**
	 * Set MultiLine Texts
	 */
	public void setText(String[] Texts)
	{
		this.texts = Texts;
		this.singleLine = false;
		this.size.height = this.fontHeight * this.texts.length;
	}
	
	public void setFont(int Color, FontSize Size, FontStyle Style){
		this.font = Font.getFont(Font.FACE_SYSTEM, Style.getValue(), Size.getValue());
		this.color = Color;
		this.fontHeight = this.font.getHeight();
		if(this.singleLine){
			this.size.height = this.fontHeight;
		} else {
			this.size.height = this.fontHeight * this.texts.length;
		}
	}
		
	public void draw(Graphics g)
	{
		if(this.visible)
		{
			g.setFont(this.font);
			g.setColor(this.color);
			if(this.singleLine){
				g.drawString(this.text, this.position.x, this.getTop(), VAlignment.TOP.anchor | this.align.horizontal.anchor);
			} else {
				for(int i = 0; i < this.texts.length; i++){
					g.drawString(this.texts[i], this.position.x, this.getTop() + (this.fontHeight * i), VAlignment.TOP.anchor | this.align.horizontal.anchor);
				}
			}
		}
	}
}
