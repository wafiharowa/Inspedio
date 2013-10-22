package com.inspedio.system.defaults;

import com.inspedio.entity.InsBitmapFont;

public class DefaultBitmapFontSmall extends InsBitmapFont{

	public DefaultBitmapFontSmall(String Text, int X, int Y) {
		super("/com/inspedio/defaults/font_small.png", 10, 12, 20, Text);
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
		else if(c == '.')
		{
			return 27;
		}
		else if(c == ',')
		{
			return 28;
		}
		else if(c == '!')
		{
			return 29;
		}
		else
		{
			return 26;
		}
	}

}
