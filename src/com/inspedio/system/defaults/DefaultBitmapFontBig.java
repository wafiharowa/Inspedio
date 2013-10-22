package com.inspedio.system.defaults;

import com.inspedio.entity.InsBitmapFont;

public class DefaultBitmapFontBig extends InsBitmapFont{

	public DefaultBitmapFontBig(String Text, int X, int Y) {
		super("/com/inspedio/defaults/font_big.png", 12, 14, 20, Text);
		this.setPosition(X, Y);
	}

	public int getCharFrame(char c) {
		if((c >= 97) && (c <=122))
		{
			// a-z
			return c-97;
		}
		else if((c >= 65) && (c <=90))
		{
			// A-Z
			return c-65;
		}
		else if((c >= 49) && (c <=57))
		{
			// 1-9
			return (c-49) + 30;
		}
		else if(c == 48)
		{
			// 0
			return 39;
		}
		else if(c == '%')
		{
			return 26;
		}
		else if(c == '/')
		{
			return 27;
		}
		else if(c == '.')
		{
			return 28;
		}
		else if(c == ',')
		{
			return 29;
		}
		else if(c == '-')
		{
			return 41;
		}
		else if(c == '!')
		{
			return 42;
		}
		else if(c == '?')
		{
			return 43;
		}
		else
		{
			return 40;
		}
	}

}
