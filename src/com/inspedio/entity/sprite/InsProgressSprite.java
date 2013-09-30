package com.inspedio.entity.sprite;

import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.InsSprite;
import com.inspedio.entity.primitive.InsPoint;
import com.inspedio.system.helper.InsUtil;

public class InsProgressSprite extends InsSprite{

	InsPoint progress;
	
	public InsProgressSprite(String spritePath) {
		super(spritePath);
		this.progress = new InsPoint(100, 100);
	}
	
	public InsProgressSprite(String spritePath, int X, int Y) {
		super(spritePath, X, Y);
		this.progress = new InsPoint(100, 100);
	}
	
	public InsProgressSprite(String spritePath, int X, int Y, int Width, int Height){
		super(spritePath, X, Y, Width, Height);
		this.progress = new InsPoint(100, 100);
	}
	
	/**
	 * Progress must be from 0-100
	 */
	public void setProgress(int progressX, int progressY){
		this.progress.x = InsUtil.Max(InsUtil.Min(InsUtil.Absolute(progressX), 100), 0); 
		this.progress.y = InsUtil.Max(InsUtil.Min(InsUtil.Absolute(progressY), 100), 0); 
	}
	
	public void addProgress(int progressX, int progressY){
		this.setProgress(this.progress.x + progressX, this.progress.y + progressY);
	}
	
	public void draw(Graphics g)
	{
		if(this.image != null)
		{
			if(absolute)
			{
				this.image.drawFrameRegion(g, this.frame, this.progress.x, this.progress.y, this.position.x, this.position.y, this.align, this.transform);
			}
			else
			{
				
				if(this.isOnScreen())
				{
					this.image.drawFrameRegion(g, this.frame, this.progress.x, this.progress.y, this.position.x - this.camera.getLeft(), this.position.y - this.camera.getTop(), this.align, this.transform);
				}
			}	
		}
	}

}
