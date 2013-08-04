package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.basic.InsBasic;
import com.inspedio.entity.primitive.InsCallback;

/**
 * Delay execution of action by given Frame Duration.
 * 
 * @author Hyude
 * @version 1.0
 */
public class Delay extends InsAction{

	/**
	 * Action to be executed after several times
	 */
	protected InsAction action;
	/**
	 * TRUE if action still delayed, FALSE if its already executed
	 */
	protected boolean delayed;
	
	protected Delay(InsBasic Target, InsCallback Callback, InsAction Action, int FrameCount) {
		super(Target, Callback, FrameCount);
		this.delayed = true;
	}
	
	public static Delay create(InsBasic Target, int DelayDuration, InsCallback Callback){
		return new Delay(Target, Callback, null, DelayDuration);
	}
	
	public static Delay create(InsBasic Target, int DelayDuration, InsAction Action, InsCallback Callback){
		return new Delay(Target, Callback, Action, DelayDuration);
	}
	

	public int act() {
		if(this.delayed){
			if(frameCount > 0){
				frameCount--;
			} else if(frameCount == 0) {
				if(this.action != null){
					this.delayed = false;
					this.frameCount = this.action.getFrameCount();
				} else {
					this.finishAction();
				}
			}
		} else {
			if(frameCount > 0) {
				if(this.action != null){
					this.frameCount = this.action.act();
				}
			} else if(frameCount == 0){
				this.finishAction();
			}
		}

		return frameCount;
	}

}
