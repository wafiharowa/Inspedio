package com.inspedio.samples;

import com.inspedio.entity.InsBitmapFont;
import com.inspedio.entity.InsState;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.defaults.DefaultBitmapFontBig;
import com.inspedio.system.defaults.DefaultBitmapFontSmall;

public class SampleFontState extends InsState{

	InsBitmapFont fontBig;
	InsBitmapFont fontSmall;
	
	
	public void create(){
		fontBig = new DefaultBitmapFontBig("SAMPLE FONT BIG", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2 - 100);
		fontSmall = new DefaultBitmapFontSmall("SAMPLE FONT SMALL", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2 + 100);
		
		this.add(fontBig);
		this.add(fontSmall);
	}
}
