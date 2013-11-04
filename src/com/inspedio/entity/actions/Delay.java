package com.inspedio.entity.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.primitive.InsCallback;

/**
 * Delay execution of action by given Frame Duration.
 * 
 * @author Hyude
 * @version 1.0
 */
public class Delay extends InsAction{

	protected Delay(int FrameCount, InsCallback Callback) {
		super(FrameCount, Callback);
	}
	
	public static Delay create(int DelayDuration, InsCallback Callback){
		return new Delay(DelayDuration, Callback);
	}
	
	public static InsAction create(int DelayDuration, InsAction Action, InsCallback Callback){
		return Sequence.create(new InsAction[] {Delay.create(DelayDuration), Action}, Callback);
	}
	
	public static Delay create(int DelayDuration){
		return create(DelayDuration, null);
	}
	

	public int act() {
		if(remainingCount > 0){
			remainingCount--;
		} else if(remainingCount == 0) {
			this.finishAction();
		}
		return remainingCount;
	}

}
