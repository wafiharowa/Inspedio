package sample.runningman;

import com.inspedio.basic.InsAnimatedObject;
import com.inspedio.basic.InsSprite;
import com.inspedio.helper.InsKeys;
import com.inspedio.helper.primitive.InsPoint;

public class Runner extends InsAnimatedObject{

	InsPoint moveTarget = null;
	int movespeed = 5;
	
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
	
	public void setMoveTarget(int X, int Y){
		if(this.moveTarget == null){
			this.moveTarget = new InsPoint(X, Y);
		} else {
			if((X != this.moveTarget.x) && (Y != this.moveTarget.y))
			{
				this.moveTarget.x = X;
				this.moveTarget.y = Y;
			}
		}
	}
	
	public void moveToTarget(){
		if(this.moveTarget != null){
			int refX = this.x + (this.width / 2);
			int refY = this.y + (this.height / 2);
			
			int deltaX = (this.moveTarget.x  - refX);
			int deltaY = (this.moveTarget.y  - refY);
			
			deltaX = (deltaX != 0) ? (deltaX > 0 ? Math.min(deltaX, movespeed) : Math.max(deltaX, -movespeed)) : 0;
			deltaY = (deltaY != 0) ? (deltaY > 0 ? Math.min(deltaY, movespeed) : Math.max(deltaY, -movespeed)) : 0;
			
			this.x += deltaX;
			this.y += deltaY;
		}
	}
	
	public void update(){
		super.update();
		this.moveToTarget();
	}
}
