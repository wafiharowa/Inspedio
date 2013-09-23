package com.inspedio.enums;

import javax.microedition.lcdui.Font;

public class FontStyle extends InsEnum{
	
	public static FontStyle PLAIN = new FontStyle("PLAIN", Font.STYLE_PLAIN);
	public static FontStyle BOLD = new FontStyle("BOLD", Font.STYLE_BOLD);
	public static FontStyle ITALIC = new FontStyle("PLAIN", Font.STYLE_ITALIC);
	public static FontStyle UNDERLINE = new FontStyle("PLAIN", Font.STYLE_UNDERLINED);
	

	protected FontStyle(String Name, int Value) {
		super(Name, Value);
	}

	
}
