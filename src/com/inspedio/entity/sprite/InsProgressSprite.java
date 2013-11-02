package com.inspedio.entity.sprite;

import javax.microedition.lcdui.Graphics;

import com.inspedio.entity.InsSprite;
import com.inspedio.entity.primitive.InsPoint;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.helper.InsUtil;
import com.inspedio.system.helper.extension.InsAlignment;

public class InsProgressSprite extends InsSprite{

	InsPoint progress;
	public InsAlignment stretch;
	
	public InsProgressSprite(String spritePath) {
		super(spritePath);
		this.initProgress();
	}
	
	public InsProgressSprite(String spritePath, int X, int Y) {
		super(spritePath, X, Y);
		this.initProgress();
	}
	
	public InsProgressSprite(String spritePath, int X, int Y, int Width, int Height){
		super(spritePath, X, Y, Width, Height);
		this.initProgress();
	}
	
	protected void initProgress(){
		this.progress = new InsPoint(100, 100);
		this.stretch = new InsAlignment(HAlignment.LEFT, VAlignment.TOP);
	}
	
	/**
	 * Direction where the Stretch started
	 */
	public void setStrecth(HAlignment horiz, VAlignment vert){
		this.stretch.setAlignment(horiz, vert);
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
				this.image.drawFrameRegion(g, this.frame, this.progress.x, this.progress.y, this.position.x, this.position.y, this.align, this.transform, this.stretch);
			}
			else
			{
				
				if(this.isOnScreen())
				{
					this.image.drawFrameRegion(g, this.frame, this.progress.x, this.progress.y, this.position.x - this.camera.getLeft(), this.position.y - this.camera.getTop(), this.align, this.transform, this.stretch);
				}
			}	
		}
	}

}
