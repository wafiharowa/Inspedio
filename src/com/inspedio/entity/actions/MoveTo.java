package com.inspedio.entity.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.InsBasic;
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
	
	protected MoveTo(InsCallback Callback, int FrameCount) {
		super(FrameCount, Callback);
	}

	public static MoveBy create(InsBasic Target, int FrameCount, int DestinationX, int DestinationY, InsCallback Callback){
		return new MoveBy(FrameCount, DestinationX - Target.position.x, DestinationY - Target.position.y, Callback);
	}

	public int act() {
		this.finishAction();
		return -1;
	}
	

}
