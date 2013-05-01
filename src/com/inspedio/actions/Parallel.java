package com.inspedio.actions;

import com.inspedio.basic.InsAction;
import com.inspedio.basic.InsBasic;
import com.inspedio.helper.primitive.InsCallback;

/**
 * Execute Several Action in the same Time.<br>
 * For example if you want to scale and move an object at the same time.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public class Parallel extends InsAction{

	protected InsAction[] actions;
	
	protected Parallel(InsBasic Target, InsAction[] ActionList, InsCallback Callback){
		super(Target, Callback, -1);
		this.actions = ActionList;
		for(int i = 0; i < this.actions.length; i++){
			this.frameCount = Math.max(this.frameCount, this.actions[i].getFrameCount());
		}
	}
	
	public int act() {
		for(int i = 0; i < this.actions.length; i++){
			this.frameCount = Math.max(this.frameCount, this.actions[i].act());
		}
		
		if(frameCount == -1){
			this.finishAction();
		}
		return frameCount;
	}
	
	public static Parallel create(InsBasic Target, InsAction[] ActionList, InsCallback Callback){
		return new Parallel(Target, ActionList, Callback);
	}

}
