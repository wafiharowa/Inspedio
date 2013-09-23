package com.inspedio.enums;

public class CollisionType extends InsEnum{

	public static CollisionType BOX = new CollisionType("BOX", 0);
	public static CollisionType SPHERE = new CollisionType("SPHERE", 1);
	
	protected CollisionType(String Name, int Value) {
		super(Name, Value);
	}

}
