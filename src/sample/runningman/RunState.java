package sample.runningman;

import com.inspedio.entity.InsState;
import com.inspedio.system.core.InsGlobal;

public class RunState extends InsState{

	Runner runner;
	
	public void create(){
		runner = new Runner(InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2);
		this.add(runner);
	}
	
	public boolean onPointerPressed(int X, int Y) {
		this.runner.setMoveTarget(X, Y);
		//System.out.println(X + "," + Y);
		return super.onPointerPressed(X, Y);
	}
}
