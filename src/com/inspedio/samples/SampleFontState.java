package com.inspedio.samples;

import com.inspedio.entity.InsBitmapFont;
import com.inspedio.entity.InsState;
import com.inspedio.entity.basic.InsText;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.defaults.DefaultBitmapFontBig;
import com.inspedio.system.defaults.DefaultBitmapFontSmall;

public class SampleFontState extends InsState{

	InsBitmapFont fontBig;
	InsBitmapFont fontSmall;
	
	InsText text;
	
	
	public void create(){
		fontBig = new DefaultBitmapFontBig("SAMPLE FONT BIG", InsGlobal.middleX, InsGlobal.middleY - 100);
		fontSmall = new DefaultBitmapFontSmall("SAMPLE FONT SMALL", InsGlobal.middleX, InsGlobal.middleY + 100);
		
		text = new InsText("SAMPLE TEXT", InsGlobal.middleX, InsGlobal.middleY);
		text.setAlignment(HAlignment.CENTER, VAlignment.TOP);
		
		this.add(fontBig);
		this.add(fontSmall);
		this.add(text);
	}
}
