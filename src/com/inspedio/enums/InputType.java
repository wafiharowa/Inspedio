package com.inspedio.enums;

public class InputType extends InsEnum{

	public static final InputType KEYPAD = new InputType("KEYPAD", 0);
	public static final InputType TOUCH = new InputType("TOUCH", 1);
	
	protected InputType(String Name, int Value) {
		super(Name, Value);
	}

}
