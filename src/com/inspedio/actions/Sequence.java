package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.basic.InsBasic;
import com.inspedio.entity.primitive.InsCallback;

/**
 * Execute Several Action in Sequence.<br>
 * For example if you want to scale followed by fading it out.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public class Sequence extends InsAction{

	protected InsAction[] actions;
	protected int currentAction;
	
	protected Sequence(InsBasic Target, InsAction[] ActionList, InsCallback Callback){
		super(Target, Callback, -1);
		this.actions = ActionList;
		this.currentAction = 0;
		for(int i = 0; i < this.actions.length; i++){
			this.frameCount = Math.max(this.frameCount, this.actions[i].getFrameCount());
		}
	}
	
	public int act() {
		if(this.actions.length > 0){
			if(this.currentAction < this.actions.length - 1){
				this.frameCount = this.actions[this.currentAction].act();
				if(frameCount == -1){
					this.currentAction++;
					this.frameCount = this.actions[this.currentAction].getFrameCount();
				}
			} else if(this.currentAction == this.actions.length - 1){
				this.actions[this.currentAction].act();
				if(frameCount == -1){
					this.finishAction();
				}
			}
		} else {
			this.finishAction();
		}
		return frameCount;
	}
	
	public static Sequence create(InsBasic Target, InsAction[] ActionList, InsCallback Callback){
		return new Sequence(Target, ActionList, Callback);
	}

}
