package com.inspedio.system.helper.collision;

import com.inspedio.entity.InsBasic;
import com.inspedio.entity.InsGroup;
import com.inspedio.enums.CollisionType;

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
								if(this.isCollide(obj1, obj2)){
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
	
	protected boolean isCollide(InsBasic obj1, InsBasic obj2){
		if(this.type == CollisionType.BOX){
			return this.isCollideBox(obj1, obj2);
		} else if (this.type == CollisionType.SPHERE){
			return this.isCollideSphere(obj1, obj2);
		}
		return false;
	}
	
	protected boolean isCollideBox(InsBasic obj1, InsBasic obj2){
		int X1 = this.getMiddleX(obj1);
		int Y1 = this.getMiddleY(obj1);
		int X2 = this.getMiddleX(obj2);
		int Y2 = this.getMiddleY(obj2);
		
		return (getAbsolute(X1 - X2) <= ((obj1.size.width + obj2.size.width) / 2) && getAbsolute(Y1 - Y2) <= ((obj1.size.height + obj2.size.height) / 2)) ;
	}
	
	protected boolean isCollideSphere(InsBasic obj1, InsBasic obj2){
		int X1 = this.getMiddleX(obj1);
		int Y1 = this.getMiddleY(obj1);
		int X2 = this.getMiddleX(obj2);
		int Y2 = this.getMiddleY(obj2);
		
		return (getDistance(X1, Y1, X2, Y2) <= ((getMax(obj1.size.width, obj1.size.height) + getMax(obj2.size.width, obj2.size.height)) / 2));
	}
	
	protected int getMiddleX(InsBasic obj){
		return obj.position.x - (((obj.align.horizontal.getValue()-1) * obj.size.width) / 2); 
	}
	
	protected int getMiddleY(InsBasic obj){
		return obj.position.y - (((obj.align.vertical.getValue()-1) * obj.size.height) / 2); 
	}
	
	protected int getAbsolute(int X){
		return (X >= 0 ? X : -X);
	}
	
	protected int getMax(int X, int Y){
		return (X >= Y ? X : Y);
	}
	
	protected double getDistance(int X1, int Y1, int X2, int Y2){
		return Math.sqrt(((X1-X2)*(X1-X2)) + ((Y1-Y2)*(Y1-Y2)));
	}
}
