package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.primitive.InsCallback;

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
	protected int stepX;
	protected int stepY;
	
	protected MoveBy(int FrameCount, int DistanceX, int DistanceY, InsCallback Callback){
		super(FrameCount, Callback);
		this.distanceX = DistanceX;
		this.distanceY = DistanceY;
		this.stepX = this.distanceX / this.frameCount;
		this.stepY = this.distanceY / this.frameCount;
	}
	
	public static MoveBy create(int FrameCount, int DistanceX, int DistanceY, InsCallback Callback){
		return new MoveBy(FrameCount, DistanceX, DistanceY, Callback);
	}
	
	public int act(){
		if(frameCount > 0){
			this.move(stepX, stepY);
			frameCount--;
		} else if(frameCount == 0){
			this.move(distanceX, distanceY);
			finishAction();
		}
		return frameCount;
	}
	
	private void move(int X, int Y){
		this.target.addPosition(X, Y);
		this.distanceX -= X;
		this.distanceY -= Y;
	}
}
