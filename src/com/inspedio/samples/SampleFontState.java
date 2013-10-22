package com.inspedio.samples;

import com.inspedio.entity.InsBitmapFont;
import com.inspedio.entity.InsShape;
import com.inspedio.entity.InsState;
import com.inspedio.entity.basic.InsText;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.entity.ui.InsButton;
import com.inspedio.enums.FontSize;
import com.inspedio.enums.FontStyle;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.defaults.DefaultBitmapFontBig;
import com.inspedio.system.defaults.DefaultBitmapFontSmall;

public class SampleFontState extends InsState{

	InsShape bg;
	InsBitmapFont fontBig;
	InsBitmapFont fontSmall;
	
	InsText text;
	
	public void create(){
		bg = new InsShape(InsGlobal.middleX, InsGlobal.middleY, InsGlobal.screenWidth, InsGlobal.screenHeight);
		bg.setBorder(0, 0);
		bg.setColor(0x666666, true);
		
		this.add(bg);
		
		fontBig = new DefaultBitmapFontBig("SAMPLE FONT BIG", InsGlobal.middleX, InsGlobal.middleY - 100);
		fontBig.setText(new String[]{"SAMPLE FOR", "FONT BIG"});
		
		fontSmall = new DefaultBitmapFontSmall("SAMPLE FONT SMALL", InsGlobal.middleX, InsGlobal.middleY + 100);
		fontSmall.setText(new String[]{"SAMPLE FOR", "FONT SMALL"});
		
		text = new InsText("SAMPLE TEXT", InsGlobal.middleX, InsGlobal.middleY);
		text.setAlignment(HAlignment.CENTER, VAlignment.TOP);
		text.setText(new String[]{"Sample For", "Basic Text"});
		
		this.add(fontBig);
		this.add(fontSmall);
		this.add(text);
		
		InsButton back = new InsButton(30, InsGlobal.screenHeight - 20, 60, 40, "BACK", 0xFFFFFF);
		back.setBorder(0, 3);
		back.setCaption("BACK", 0, FontSize.LARGE, FontStyle.BOLD);
		back.setClickedCallback(new InsCallback() {
			public void call() {
				InsGlobal.switchState(new SampleButtonState(), false);
			}
		});
		
		this.add(back);
	}
	
	public void onLeftSoftKey()
	{
		InsGlobal.switchState(new SampleButtonState(), false);
	}
}
