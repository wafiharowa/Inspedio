package sample.runningman;

import com.inspedio.basic.InsState;
import com.inspedio.basic.ui.InsButton;
import com.inspedio.core.InsCanvas;
import com.inspedio.core.InsGlobal;
import com.inspedio.helper.primitive.InsCallback;

public class MenuState extends InsState{

	InsButton play;
	InsButton options;
	InsButton credits;
	InsButton exit;
	
	public void create(){
		int midX = InsGlobal.screenWidth / 2;
		int midY = InsGlobal.screenHeight / 2;
		
		this.play = new InsButton(midX, midY - 90, 140, 40, "PLAY", InsCanvas.COLOR_RED);
		this.options = new InsButton(midX, midY - 30, 140, 40, "OPTIONS", InsCanvas.COLOR_RED);
		this.credits = new InsButton(midX, midY + 30, 140, 40, "CREDITS", InsCanvas.COLOR_RED);
		this.exit = new InsButton(midX, midY + 90, 140, 40, "EXIT", InsCanvas.COLOR_RED);
		
		this.play.setClickedCallback(new InsCallback() {
			
			public void call() {
				InsGlobal.switchState(new RunState(), false);
			}
		});
		
		this.add(this.play);
		this.add(this.options);
		this.add(this.credits);
		this.add(this.exit);
	}
}
