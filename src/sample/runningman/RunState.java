package sample.runningman;

import com.inspedio.basic.InsState;
import com.inspedio.core.InsGlobal;

public class RunState extends InsState{

	Runner runner;
	
	public void create(){
		runner = new Runner(InsGlobal.screenWidth / 2, InsGlobal.screenHeight / 2);
		this.add(runner);
	}
}
