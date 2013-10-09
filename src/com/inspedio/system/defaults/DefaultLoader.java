package com.inspedio.system.defaults;

import com.inspedio.entity.InsShape;
import com.inspedio.entity.basic.InsText;
import com.inspedio.enums.HAlignment;
import com.inspedio.enums.VAlignment;
import com.inspedio.system.core.InsCanvas;
import com.inspedio.system.core.InsGlobal;
import com.inspedio.system.core.InsLoader;

public class DefaultLoader extends InsLoader{

	InsText progressText;
	InsText loading;
	InsShape loadBar;
	InsShape borderBar;
	
	int borderWidth = 3;
	int loadWidth = 180;
	int loadHeight = 40;
	
	
	public void create() {
		int midX = InsGlobal.middleX;
		int midY = InsGlobal.middleY;
		
		this.loading = new InsText("LOADING...", midX, midY - 50);
		
		this.borderBar = new InsShape(midX, midY + 50, loadWidth + (borderWidth * 2), loadHeight + (borderWidth * 2));
		this.borderBar.setRoundedRect(loadWidth + (borderWidth * 2), loadHeight + (borderWidth * 2), 10, 10);
		this.borderBar.setColor(InsCanvas.COLOR_WHITE, true);
		this.borderBar.setBorder(InsCanvas.COLOR_BLACK, borderWidth);
		
		this.loadBar = new InsShape(midX, midY + 50, loadWidth, loadHeight);
		this.loadBar.setRoundedRect(loadWidth, loadHeight, 10, 10);
		this.loadBar.setColor(InsCanvas.COLOR_GREEN, true);
		this.loadBar.setBorder(InsCanvas.COLOR_BLACK, 0);
		this.loadBar.setPosition(this.loadBar.getLeft(), this.loadBar.getTop());
		this.loadBar.setAlignment(HAlignment.LEFT, VAlignment.TOP);
		
		this.progressText = new InsText("0 %", midX, midY + 50);
		
		this.add(this.loading);
		this.add(this.borderBar);
		this.add(this.loadBar);
		this.add(this.progressText);
	}
	
	public void progressChanged(int CurrentProgress) {
		this.progressText.setText(CurrentProgress + " %");
		this.loadBar.setSize((int) ((CurrentProgress * loadWidth )/ 100), this.loadHeight);
	}	

}
