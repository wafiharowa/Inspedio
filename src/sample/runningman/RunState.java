package sample.runningman;

import com.inspedio.actions.MoveTo;
import com.inspedio.entity.InsSprite;
import com.inspedio.entity.InsState;
import com.inspedio.system.core.InsGlobal;

public class RunState extends InsState{

	InsSprite runner;
	
	public void create(){
		runner = new InsSprite("sample/runner/runner.png", InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2, 32, 48);
		this.add(runner);
	}
	
	public boolean onPointerPressed(int X, int Y) {
		this.runner.setAction(MoveTo.create(this.runner, X, Y, 10, null));
		//System.out.println(X + "," + Y);
		return super.onPointerPressed(X, Y);
	}
}
