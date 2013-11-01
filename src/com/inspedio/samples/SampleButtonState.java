package com.inspedio.samples;

import com.inspedio.entity.InsState;
import com.inspedio.entity.ui.InsButton;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.enums.BorderLayout;
import com.inspedio.enums.FontSize;
import com.inspedio.enums.FontStyle;
import com.inspedio.system.core.InsCanvas;
import com.inspedio.system.core.InsGlobal;

public class SampleButtonState extends InsState{

	InsButton SPRITE;
	InsButton FONT;
	InsButton ACTION;
	InsButton EXIT;
	
	public void create(){
		int midX = InsGlobal.screenWidth / 2;
		int midY = InsGlobal.screenHeight / 2;
		
		int width =140;
		int height =40;
		
		
		this.SPRITE = new InsButton(midX, midY - 90, width, height, "SPRITE", InsCanvas.COLOR_RED);
		this.FONT = new InsButton(midX, midY - 30, width, height, "FONT", InsCanvas.COLOR_GREEN);
		this.ACTION = new InsButton(midX, midY + 30, width, height, "ACTION", InsCanvas.COLOR_BLUE);
		this.EXIT = new InsButton(midX, midY + 90, width, height, "EXIT", InsCanvas.COLOR_YELLOW);
		
		SPRITE.setRoundedRect(140, 40, 40, 40);
		SPRITE.setBorder(InsCanvas.COLOR_BLACK, 6);
		SPRITE.setClickedCallback(new InsCallback() {
			public void call() {
				InsGlobal.switchState(new SampleSpriteState(), true);
			}
		});
		
		FONT.setCaption("FONT", InsCanvas.COLOR_BLACK, FontSize.MEDIUM, FontStyle.ITALIC);
		FONT.setClickedCallback(new InsCallback() {
			public void call() {
				InsGlobal.switchState(new SampleFontState(), true);
			}
		});
		
		ACTION.setBorder(InsCanvas.COLOR_RED, 6, BorderLayout.OUTSIDE);
		ACTION.setCaption("ACTION", InsCanvas.COLOR_WHITE, FontSize.MEDIUM, FontStyle.BOLD);
		ACTION.setClickedCallback(new InsCallback() {
			public void call() {
				InsGlobal.switchState(new SampleActionState(), true);
			}
		});
		
		EXIT.setBorder(InsCanvas.COLOR_BLACK, 6, BorderLayout.CENTER);
		EXIT.setClickedCallback(new InsCallback() {
			public void call() {
				InsGlobal.save.save();
				InsGlobal.exitGame();
			}
		});
		
		this.add(this.SPRITE);
		this.add(this.FONT);
		this.add(this.ACTION);
		this.add(this.EXIT);
	}
}
