package com.inspedio.enums;


public class ShapeType extends InsEnum{

	public static final ShapeType RECTANGLE = new ShapeType("RECTANGLE", 0);
	public static final ShapeType ROUNDED_RECT = new ShapeType("ROUNDED_RECT", 0);
	public static final ShapeType CIRCLE = new ShapeType("CIRCLE", 0);
	
	protected ShapeType(String Name, int Value) {
		super(Name, Value);
	}

}
