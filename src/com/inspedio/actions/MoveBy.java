package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.system.helper.InsUtil;

/**
 * Move Target by given distance during given Frame Duration (Higher Frame Duration means slower animation).<br>
 * For example, moving 100 distance during 10 Frame means every frame moves target by 10 distance.<br>
 * You can set Callback to be executed when Action ended.<br> 
 * 
 * @author Hyude
 * @version 1.0
 */
public class MoveBy extends InsAction{

	protected int distanceX;
	protected int distanceY;
	
	protected int remainingX;
	protected int remainingY;
	
	protected double stepX;
	protected double stepY;
	
	protected double offsetX;
	protected double offsetY;
	
	
	protected MoveBy(int FrameCount, int DistanceX, int DistanceY, InsCallback Callback){
		super(FrameCount, Callback);
		this.distanceX = DistanceX;
		this.distanceY = DistanceY;
		this.stepX = (double) this.distanceX / (double) this.remainingCount;
		this.stepY = (double) this.distanceY / (double) this.remainingCount;
		this.reset();
	}
	
	public static MoveBy create(int FrameCount, int DistanceX, int DistanceY, InsCallback Callback){
		return new MoveBy(FrameCount, DistanceX, DistanceY, Callback);
	}
	
	public int act(){
		if(remainingCount > 0){
			this.move(stepX, stepY);
			remainingCount--;
		} else if(remainingCount == 0){
			this.move(distanceX, distanceY);
			finishAction();
		}
		return remainingCount;
	}
	
	private void move(double X, double Y){
		this.target.addPosition((int) X, (int)Y);
		this.offsetX += X - (int) X;
		this.offsetY += Y - (int) Y;
		this.remainingX -= X;
		this.remainingY -= Y;
		if(InsUtil.Absolute((int) offsetX) >= 1){
			this.target.addPosition((int) this.offsetX, 0);
			this.offsetX -= (int) this.offsetX;
		}
		if(InsUtil.Absolute((int) offsetY) >= 1){
			this.target.addPosition(0, (int) this.offsetY);
			this.offsetY -= (int) this.offsetY;
		}
	}
	
	protected void reset(){
		super.reset();
		this.remainingX = this.distanceX;
		this.remainingY = this.distanceY;
		this.offsetX = 0;
		this.offsetY = 0;
	}
}
