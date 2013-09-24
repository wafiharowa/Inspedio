package com.inspedio.system.helper.collision;

import com.inspedio.entity.InsBasic;
import com.inspedio.entity.InsGroup;
import com.inspedio.enums.CollisionType;
import com.inspedio.system.helper.InsUtil;

public class InsCollisionHandler {

	protected InsGroup group1 = null;
	protected InsGroup group2 = null;
	protected InsCollisionCallback callback = null;
	protected CollisionType type;
	public String name;
	
	public InsCollisionHandler(String Name, InsGroup g1, InsGroup g2, InsCollisionCallback call, CollisionType Type){
		this.name = Name;
		this.group1 = g1;
		this.group2 = g2;
		this.callback = call;
		this.type = Type;
	}
	
	public void handle(){
		if(this.isValid()){
			if(!this.group1.deleted && !this.group2.deleted)
			{
				InsBasic obj1;
				InsBasic obj2;
				for(int i = 0; i < this.group1.members.size(); i++)
				{
					obj1 = (InsBasic) this.group1.members.elementAt(i);
					if((obj1 != null) && obj1.exists && obj1.alive)
					{
						for(int j = 0; j < this.group2.members.size(); j++)
						{
							obj2 = (InsBasic) this.group2.members.elementAt(j);
							if((obj2 != null) && obj2.exists && obj2.alive)
							{
								if(InsUtil.isCollide(obj1, obj2, this.type)){
									this.callback.call(obj1, obj2);
								}
							}
						}
					}
				}
			}
		}
	}
	
	protected boolean isValid(){
		return ((this.group1 != null) && (this.group2 != null) && (this.callback != null));
	}
	
}
