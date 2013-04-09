package com.inspedio.basic;

import java.io.IOException;

import javax.microedition.lcdui.Image;

import com.inspedio.core.InsGlobal;

/**
 * InsImage is primitives for Image (not Sprite). Use this for Background or other large single image.
 * 
 * @author Hyude
 * @version 1.0
 */
public class InsImage extends InsBasic{

	public String filepath;
	public Image image;
	/**
	 * Construct Image from given filePath
	 */
	public InsImage(String Filepath)
	{
		super(0, 0, 0, 0);
		this.filepath = Filepath;
		try
		{
			this.image = Image.createImage(Filepath);
			this.width = this.image.getWidth();
			this.height = this.image.getHeight();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw()
	{
		if(this.isOnScreen())
		{
			InsGlobal.graphic.drawImage(this.image, this.x, this.y, 0);
		}
	}
	
	public void destroy()
	{
		super.destroy();
		this.image = null;
	}
	
	/**
	 * Check whether the image is currently on screen camera
	 * 
	 * @return	TRUE if the image is on screen
	 */
	public boolean isOnScreen()
	{
		return (this.x < InsGlobal.screenWidth) && (0 < this.x + this.image.getWidth()) && (this.y < InsGlobal.screenHeight) && (0 < this.y + this.image.getHeight());
	}
}
