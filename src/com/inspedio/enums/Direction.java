package com.inspedio.enums;

public class Direction extends InsEnum{

	public static final Direction TOP = new Direction("TOP", 1);
	public static final Direction BOTTOM = new Direction("BOTTOM", 2);
	public static final Direction LEFT = new Direction("LEFT", 3);
	public static final Direction RIGHT = new Direction("RIGHT", 4);
	
	
	protected Direction(String Name, int Value) {
		super(Name, Value);
	}

}
