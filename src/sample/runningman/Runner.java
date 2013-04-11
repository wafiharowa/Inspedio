package sample.runningman;

import com.inspedio.basic.InsAnimatedObject;
import com.inspedio.basic.InsSprite;
import com.inspedio.helper.InsKeys;

public class Runner extends InsAnimatedObject{

	public Runner(int X, int Y){
		super();
		this.setSprite(new InsSprite("/sample/runner/runner.png", 32, 48));
		this.setMiddlePoint(X, Y);
		this.addAnimation("DOWN", new int[]{0,1,2,3,2,1}, null);
		this.addAnimation("LEFT", new int[]{4,5,6,7,6,5}, null);
		this.addAnimation("RIGHT", new int[]{8,9,10,11,10,9}, null);
		this.addAnimation("UP", new int[]{12,13,14,15,14,13}, null);
		this.addAnimation("IDLE", new int[]{0}, null);
		this.playAnimation("IDLE");
	}
	
	public void handleKeyState(InsKeys keys){
		if(keys.keyPressed(InsKeys.DOWN)){
			this.playAnimation("DOWN");
			System.out.println("PRESSED DOWN");
			this.y++;
		}
		else if(keys.keyPressed(InsKeys.UP)){
			this.playAnimation("UP");
			System.out.println("PRESSED UP");
			this.y--;
		}
		else if(keys.keyPressed(InsKeys.LEFT)){
			this.playAnimation("LEFT");
			System.out.println("PRESSED LEFT");
			this.x--;
		}
		else if(keys.keyPressed(InsKeys.RIGHT)){
			this.playAnimation("RIGHT");
			System.out.println("PRESSED RIGHT");
			this.x++;
		} else{
			this.playAnimation("IDLE");
		}
	}
}
