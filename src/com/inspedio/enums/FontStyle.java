package com.inspedio.enums;

import javax.microedition.lcdui.Font;

public class FontStyle extends InsEnum{
	
	public static final FontStyle PLAIN = new FontStyle("PLAIN", Font.STYLE_PLAIN);
	public static final FontStyle BOLD = new FontStyle("BOLD", Font.STYLE_BOLD);
	public static final FontStyle ITALIC = new FontStyle("PLAIN", Font.STYLE_ITALIC);
	public static final FontStyle UNDERLINE = new FontStyle("PLAIN", Font.STYLE_UNDERLINED);
	

	protected FontStyle(String Name, int Value) {
		super(Name, Value);
	}

	
}
