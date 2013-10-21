package com.inspedio.actions;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.InsBasic;
import com.inspedio.entity.primitive.InsCallback;

/**
 * Execute Several Action in the same Time.<br>
 * For example if you want to scale and move an object at the same time.<br>
 * 
 * @author Hyude
 * @version 1.0
 */
public class Parallel extends InsAction{

	protected InsAction[] actions;
	
	protected Parallel(InsAction[] ActionList, InsCallback Callback){
		super(-1, Callback);
		this.actions = ActionList;
		for(int i = 0; i < this.actions.length; i++){
			this.remainingCount = Math.max(this.remainingCount, this.actions[i].getRemainingCount());
		}
	}
	
	public int act() {
		for(int i = 0; i < this.actions.length; i++){
			this.actions[i].act();
		}
		
		if(remainingCount > 0){
			for(int i = 0; i < this.actions.length; i++){
				this.actions[i].act();
			}
			remainingCount--;
		} else if(remainingCount == 0){
			for(int i = 0; i < this.actions.length; i++){
				this.actions[i].act();
			}
			finishAction();
		}
		return remainingCount;
	}
	
	public void setTarget(InsBasic Target){
		this.target = Target;
		for(int i = 0; i < this.actions.length; i++){
			this.actions[i].setTarget(Target);
		}
	}
	
	public static Parallel create(InsAction[] ActionList, InsCallback Callback){
		return new Parallel(ActionList, Callback);
	}

}
