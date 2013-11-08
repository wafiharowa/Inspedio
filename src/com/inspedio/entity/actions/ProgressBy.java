package com.inspedio.entity.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.entity.sprite.InsProgressSprite;
import com.inspedio.system.helper.InsUtil;

/**
 * This action only works for InsProgressSprite
 */

public class ProgressBy extends InsAction{

	protected int progressX;
	protected int progressY;
	
	protected int remainingX;
	protected int remainingY;
	
	protected double stepX;
	protected double stepY;
	
	protected double offsetX;
	protected double offsetY;
	
	protected ProgressBy(int FrameCount, int ProgressX, int ProgressY, InsCallback Callback) {
		super(FrameCount, Callback);
		this.progressX = ProgressX;
		this.progressY = ProgressY;
		this.stepX = (double) this.progressX / (double) FrameCount;
		this.stepY = (double) this.progressY / (double) FrameCount;
		this.reset();
	}
	
	public static ProgressBy create(int FrameCount, int ProgressX, int ProgressY, InsCallback Callback){
		return new ProgressBy(FrameCount, ProgressX, ProgressY, Callback);
	}
	
	public static ProgressBy create(int FrameCount, int ProgressX, int ProgressY){
		return create(FrameCount, ProgressX, ProgressY, null);
	}
	
	public int act(){
		if(remainingCount > 0){
			this.progress(stepX, stepY);
			remainingCount--;
		} else if(remainingCount == 0){
			this.progress(stepX, stepY);
			finishAction();
		}
		return remainingCount;
	}
	
	private void progress(double X, double Y){
		((InsProgressSprite) this.target).addProgress((int) X, (int)Y);
		this.offsetX += X - (int) X;
		this.offsetY += Y - (int) Y;
		this.remainingX -= X;
		this.remainingY -= Y;
		if(InsUtil.Absolute((int) offsetX) >= 1){
			((InsProgressSprite) this.target).addProgress((int) this.offsetX, 0);
			this.offsetX -= (int) this.offsetX;
		}
		if(InsUtil.Absolute((int) offsetY) >= 1){
			((InsProgressSprite) this.target).addProgress(0, (int) this.offsetY);
			this.offsetY -= (int) this.offsetY;
		}
	}
	
	public void reset(){
		super.reset();
		this.remainingX = this.progressX;
		this.remainingY = this.progressY;
		this.offsetX = 0;
		this.offsetY = 0;
	}

}
