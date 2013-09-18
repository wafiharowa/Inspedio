package com.inspedio.system.helper.collision;

import java.util.Vector;

import com.inspedio.entity.InsGroup;
import com.inspedio.enums.CollisionType;

public class InsCollisionDetector {
	
	private Vector handlerList;

	/**
	 * Add new Collision Handler. return FALSE if there is already handler with given.
	 */
	public boolean addHandler(String HandlerName, InsGroup group1, InsGroup group2, InsCollisionCallback call, CollisionType Type){
		int idx = this.checkHandler(HandlerName);
		if(idx == -1){
			this.handlerList.addElement(new InsCollisionHandler(HandlerName, group1, group2, call, Type));
			return true;
		}
		return false;
		
	}
	
	public boolean removeHandler(String HandlerName){
		int idx = this.checkHandler(HandlerName);
		if(idx != -1){
			this.handlerList.removeElementAt(idx);
			return true;
		}
		return false;
	}
	
	public void handleAllCollision(){
		for(int i = 0; i < this.handlerList.size(); i++)
		{
			InsCollisionHandler h = (InsCollisionHandler) this.handlerList.elementAt(i);
			h.handle();
		}
	}
	
	protected int checkHandler(String handlerName)
	{
		int foundIdx = -1;
		for(int i = 0; i < this.handlerList.size(); i++)
		{
			InsCollisionHandler h = (InsCollisionHandler) this.handlerList.elementAt(i);
			if(h.name.equals(handlerName))
			{
				foundIdx = i;
				break;
			}
		}
		
		return foundIdx;
	}
}
