package com.inspedio.enums;

public class CollisionType extends InsEnum{

	public static final CollisionType BOX = new CollisionType("BOX", 0);
	public static final CollisionType SPHERE = new CollisionType("SPHERE", 1);
	
	protected CollisionType(String Name, int Value) {
		super(Name, Value);
	}

}
