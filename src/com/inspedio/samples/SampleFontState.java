package com.inspedio.samples;

import com.inspedio.entity.InsBitmapFont;
import com.inspedio.entity.InsState;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.defaults.DefaultBitmapFont;

public class SampleFontState extends InsState{

	InsBitmapFont font;
	
	public void create(){
		font = new DefaultBitmapFont("SAMPLE FONT", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2);
		this.add(font);
	}
}
