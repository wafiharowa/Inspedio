package com.inspedio.samples;

import com.inspedio.entity.InsAction;
import com.inspedio.entity.InsShape;
import com.inspedio.entity.InsState;
import com.inspedio.entity.actions.Delay;
import com.inspedio.entity.actions.Forever;
import com.inspedio.entity.actions.MoveBy;
import com.inspedio.entity.actions.ScaleBy;
import com.inspedio.entity.actions.Sequence;
import com.inspedio.entity.primitive.InsCallback;
import com.inspedio.entity.ui.InsButton;
import com.inspedio.enums.BorderLayout;
import com.inspedio.enums.FontSize;
import com.inspedio.enums.FontStyle;
import com.inspedio.system.core.InsCanvas;
import com.inspedio.system.core.InsGlobal;

public class SampleActionState extends InsState{

	InsShape obj;
	InsButton sequence;
	InsButton parallel;
	InsButton forever;
	
	int curBorder = 0;
	
	public void create() {
		int midX = InsGlobal.screenWidth / 2;
		int midY = InsGlobal.screenHeight / 2;
		
		this.obj = new InsShape(midX, midY - 100, 50, 50);
		this.obj.setColor(InsCanvas.COLOR_RED, true);
		this.obj.setCircle(25, 0, 360);
		this.obj.setBorder(0, 2, BorderLayout.OUTSIDE);
		
		this.sequence = new InsButton(midX - 60, midY, 100, 60, "SEQUENCE", InsCanvas.COLOR_GREEN);
		this.sequence.setCaption("SEQUENCE", InsCanvas.COLOR_BLACK, FontSize.SMALL, FontStyle.PLAIN);
		this.sequence.setClickedCallback(new InsCallback() {
			public void call() {
				obj.setAction(MoveBy.create(20, 60, -20, null), true);
				obj.appendAction(MoveBy.create(20, -60, 20, null));
			}
		});
		
		this.parallel = new InsButton(midX + 60, midY, 100, 60, "PARALLEL", InsCanvas.COLOR_GREEN);
		this.parallel.setCaption("PARALLEL", InsCanvas.COLOR_BLACK, FontSize.SMALL, FontStyle.PLAIN);
		this.parallel.setClickedCallback(new InsCallback() {
			public void call() {
				obj.setAction(Sequence.create(new InsAction[]{MoveBy.create(20, -40, -20, null), MoveBy.create(20, 40, 20, null)}, null), true);
				obj.combineAction(Sequence.create(new InsAction[]{ScaleBy.create(20, 20, 20, null), ScaleBy.create(20, -20, -20, null)}, null));
			}
		});
		
		this.forever = new InsButton(midX, midY + 70, 100, 60, "FOREVER", InsCanvas.COLOR_GREEN);
		this.forever.setCaption("FOREVER", InsCanvas.COLOR_BLACK, FontSize.SMALL, FontStyle.PLAIN);
		this.forever.setClickedCallback(new InsCallback() {
			public void call() {
				obj.setAction(Forever.create(Delay.create(3, new InsCallback() {	
					public void call() {
						curBorder = (curBorder + 2) % 8;
						obj.setBorder(0, 2 + curBorder);
					}
				})), true);
			}
		});
		
		this.add(this.obj);
		this.add(this.sequence);
		this.add(this.parallel);
		this.add(this.forever);
		
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
