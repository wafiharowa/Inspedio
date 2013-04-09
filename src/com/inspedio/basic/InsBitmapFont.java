package com.inspedio.basic;

import com.inspedio.core.InsGlobal;

/**
 * <code>InsBitmapFont</code> is a spritesheet which consists of alphanumeric or symbols.<br>
 * Used to display custom font with image. <br>
 * You can set Text to be displayed, but have to make sure you define charMapping in <code>getCharFrame(c)</code> method.
 * 
 * @author Hyude
 * @version 1.0
 */
public abstract class InsBitmapFont extends InsBasic{

	public static final int ALIGN_LEFT = 0;
	public static final int ALIGN_CENTER = 1;
	public static final int ALIGN_RIGHT = 2;
	
	public static final int ALIGN_TOP = 0;
	public static final int ALIGN_MIDDLE = 1;
	public static final int ALIGN_BOTTOM = 2;
	
	/**
	 * Sprite that contains Bitmap FontSheet
	 */
	protected InsSprite fontsheet;
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
	 * Vertical Alignment (TOP, MIDDLE, BOTTOM)
	 */
	public int vAlign;
	/**
	 * Horizontal Alignment (LEFT, CENTER, RIGHT)
	 */
	public int hAlign;
	/**
	 * Maximum Allowed Size of text. If you set text longer than maximum size, it will be cropped.
	 */
	public int maxSize;
	
	public InsBitmapFont(String imagePath, int fontWidth, int fontHeight, int maxSize)
	{
		super(0, 0, 0, 0);
		this.currentText = "";
		this.fontsheet = InsGlobal.cache.getSprite(imagePath, fontWidth, fontHeight);
		this.length = 0;
		this.maxSize = maxSize;
		this.frameChar = new int[this.maxSize];
		this.setAttribute(0, 0, ALIGN_BOTTOM, ALIGN_LEFT);
	}
	
	/**
	 * Set BtmapFont attribute
	 */
	public void setAttribute(int X, int Y, int verticalAlign, int horizontalAlign)
	{
		this.x = X;
		this.y = Y;
		this.vAlign = verticalAlign;
		this.hAlign = horizontalAlign;
	}
	
	public void draw()
	{
		super.draw();
		if(this.frameChar != null)
		{
			int refX = 0;
			int refY = 0;
			if(this.hAlign == ALIGN_LEFT)
			{
				refX = this.x;
			}
			else if(this.hAlign == ALIGN_CENTER)
			{
				refX = this.x - ((this.length * this.fontsheet.frameWidth)/ 2);
			}
			else if(this.hAlign == ALIGN_RIGHT)
			{
				refX = this.x - (this.length * this.fontsheet.frameWidth);
			}
			
			if(this.vAlign == ALIGN_TOP)
			{
				refY = this.y;
			}
			else if(this.vAlign == ALIGN_MIDDLE)
			{
				refY = this.y - (this.fontsheet.frameHeight/2);
			}
			else if(this.vAlign == ALIGN_BOTTOM)
			{
				refY = this.y - this.fontsheet.frameHeight;
			}
			
			for(int i = 0; i < this.length; i++)
			{
				this.fontsheet.setFrame(this.frameChar[i]);
				this.fontsheet.setPosition(refX + (i * this.fontsheet.frameWidth), refY);
				this.fontsheet.draw();
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
