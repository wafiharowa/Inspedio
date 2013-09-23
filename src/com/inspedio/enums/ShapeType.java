package com.inspedio.enums;


public class ShapeType extends InsEnum{

	public static ShapeType RECTANGLE = new ShapeType("RECTANGLE", 0);
	public static ShapeType ROUNDED_RECT = new ShapeType("ROUNDED_RECT", 0);
	public static ShapeType CIRCLE = new ShapeType("CIRCLE", 0);
	
	protected ShapeType(String Name, int Value) {
		super(Name, Value);
	}

}
