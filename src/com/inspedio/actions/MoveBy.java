package com.inspedio.actions;

import com.inspedio.basic.InsAction;
import com.inspedio.basic.ui.InsUI;
import com.inspedio.helper.primitive.InsCallback;

/**
 * 
 * 
 * @author Hyude
 * @version 1.0
 */
public class MoveBy extends InsAction{

	protected int distanceX;
	protected int distanceY;	
	protected int stepX;
	protected int stepY;
	protected int frameCount;
	protected InsCallback callback;
	protected InsUI target;
	
	private MoveBy(InsUI Target, int DistanceX, int DistanceY, int FrameCount, InsCallback Callback){
		this.target = Target;
		this.distanceX = DistanceX;
		this.distanceY = DistanceY;
		this.frameCount = FrameCount;
		this.callback = Callback;
		this.stepX = this.distanceX / this.frameCount;
		this.stepY = this.distanceY / this.frameCount;
	}
	
	public static MoveBy create(InsUI Target, int DistanceX, int DistanceY, int FrameCount, InsCallback Callback){
		return new MoveBy(Target, DistanceX, DistanceY, FrameCount, Callback);
	}
	
	public void act(){
		if(frameCount > 0){
			
			frameCount--;
		} else if(frameCount == 0){
			if(this.callback != null){
				this.callback.call();
			}
			frameCount = -1;
		}
	}
}
