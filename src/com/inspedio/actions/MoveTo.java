package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.basic.InsBasic;
import com.inspedio.entity.primitive.InsCallback;

/**
 * Move Target to given position during F Frame Duration (Higher Frame Duration means slower animation).<br>
 * For example, moving 100 distance during 10 Frame means every frame moves target by 10 distance.<br>
 * You can set Callback to be executed when Action ended.<br> 
 * 
 * @author Hyude
 * @version 1.0
 */
public class MoveTo extends InsAction{
	
	protected MoveTo(InsBasic Target, InsCallback Callback, int FrameCount) {
		super(Target, Callback, FrameCount);
	}

	public static MoveBy create(InsBasic Target, int DestinationX, int DestinationY, int FrameCount, InsCallback Callback){
		return new MoveBy(Target, DestinationX - Target.x, DestinationY - Target.y, FrameCount, Callback);
	}

	public int act() {
		this.finishAction();
		return -1;
	}
	

}
