package com.inspedio.enums;

import javax.microedition.lcdui.Font;

public class FontSize extends InsEnum{

	public static final FontSize SMALL = new FontSize("SMALL", Font.SIZE_SMALL);
	public static final FontSize MEDIUM = new FontSize("MEDIUM", Font.SIZE_MEDIUM);
	public static final FontSize LARGE = new FontSize("LARGE", Font.SIZE_LARGE);
	

	protected FontSize(String Name, int Value) {
		super(Name, Value);
	}
}
