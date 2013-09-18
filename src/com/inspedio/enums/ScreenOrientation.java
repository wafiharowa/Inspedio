package com.inspedio.enums;

public class ScreenOrientation extends InsEnum{

	public static final ScreenOrientation PORTRAIT = new ScreenOrientation("PORTRAIT", 1);
	public static final ScreenOrientation LANDSCAPE = new ScreenOrientation("LANDSCAPE", 2);
	
	protected ScreenOrientation(String Name, int Value) {
		super(Name, Value);
	}
}
