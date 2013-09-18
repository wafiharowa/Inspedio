package com.inspedio.entity;

import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.basic.InsImage;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.TransformType;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.core.InsGlobal;

/**
 * <code>InsBitmapFont</code> is a spritesheet which consists of alphanumeric or symbols.<br>
 * Used to display custom font with image. <br>
 * You can set Text to be displayed, but have to make sure you define charMapping in <code>getCharFrame(c)</code> method.
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsBitmapFont extends InsBasic{
	
	/**
	 * Sprite that contains Bitmap FontSheet
	 */
	protected InsImage fontsheet;
	/**
	 * current Text displayed by bitmapFont
	 */
	protected String currentText;
	/**
	 * Frame Array of current text
	 */
	protected int[] frameChar;
	/**
	 * Length of current Text
	 */
	protected int length;
	/**
	 * Maximum Allowed Size of text. If you set text longer than maximum size, it will be cropped.
	 */
	public int maxSize;
	
	public InsBitmapFont(String imagePath, int fontWidth, int fontHeight, int maxSize)
	{
		super(0, 0, 0, 0);
		this.fontsheet = InsGlobal.cache.getImage(imagePath, fontWidth, fontHeight);
		this.currentText = "";
		this.length = 0;
		this.maxSize = maxSize;
		this.frameChar = new int[this.maxSize];
		this.align.setAlignment(HAlignment.LEFT, VAlignment.BOTTOM);
	}
	
	
	public void draw(Graphics g)
	{
		if(this.frameChar != null)
		{
			int refX = this.position.x - (this.align.horizontal.getValue() * ((this.length * this.fontsheet.frameWidth)/ 2));
			int refY = this.position.y - (this.align.vertical.getValue() * (this.fontsheet.frameHeight/ 2));
			
			for(int i = 0; i < this.length; i++)
			{
				this.fontsheet.drawFrame(g, this.frameChar[i], refX + (i * this.fontsheet.frameWidth), refY, this.align, TransformType.NONE);
			}
		}
	}
	
	/**
	 * Get the corresponding frame for given char<br>
	 * Override this to implement your own BitmapFont
	 */
	public abstract int getCharFrame(char c);
	
	/**
	 * This is how you set Text. Do not try to modify text without this method.
	 */
	public void setText(String Text)
	{
		this.length = Math.min(this.maxSize, Text.length());
		for(int i = 0; i < this.length; i++)
		{
			this.frameChar[i] = this.getCharFrame(Text.charAt(i)) % this.fontsheet.frameTotal;
		}
		
		this.currentText = Text;
	}
	
	public int getWidth()
	{
		return (this.length * this.fontsheet.frameWidth);
	}
	
	public int getHeight()
	{
		return this.fontsheet.frameHeight;
	}
}
