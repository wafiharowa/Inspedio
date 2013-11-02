package com.inspedio.system.defaults;

import com.inspedio.entity.InsShape;
import com.inspedio.entity.basic.InsText;
import com.inspedio.enums.BorderLayout;
import com.inspedio.enums.FontSize;
import com.inspedio.enums.FontStyle;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.InputType;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.core.InsCanvas;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.helper.InsPause;

public class DefaultPause extends InsPause{

	InsShape box;
	InsText title;
	InsText info;
		
	protected void initPause(){
		box = new InsShape(InsGlobal.middleX, InsGlobal.middleY, 200, 140);
		box.setColor(InsCanvas.COLOR_WHITE, true);
		box.setBorder(InsCanvas.COLOR_BLACK, 5, BorderLayout.OUTSIDE);
		box.setRoundedRect(40, 40);
		
		title = new InsText("GAME IS PAUSED", InsGlobal.middleX, InsGlobal.middleY - 10);
		title.setAlignment(HAlignment.CENTER, VAlignment.BOTTOM);
		title.setFont(InsCanvas.COLOR_BLACK, FontSize.LARGE, FontStyle.BOLD);
		
		info = new InsText("Touch / Press", InsGlobal.middleX, InsGlobal.middleY + 10);
		info.setAlignment(HAlignment.CENTER, VAlignment.TOP);
		info.setFont(InsCanvas.COLOR_BLACK, FontSize.SMALL, FontStyle.PLAIN);
		
		this.add(box);
		this.add(title);
		this.add(info);
	}
	
	public void showPause(InputType Input) {
		this.initPause();
		if(Input == InputType.TOUCH){
			this.info.setText(new String[]{"Touch anywhere", "to continue"});
		} else if(Input == InputType.KEYPAD){
			this.info.setText(new String[]{"Press anything", "to continue"});
		}
	}
}
